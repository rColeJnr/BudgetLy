package com.rick.budgetly.feature_bills.ui

import android.content.res.Resources
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rick.budgetly.BudgetLyContainer
import com.rick.budgetly.R
import com.rick.common.BaseLogic
import com.rick.bill_data.domain.Bill
import com.rick.bill_data.domain.BillIcon
import com.rick.bill_data.domain.InvalidBillException
import com.rick.bill_data.domain.use_case.BillUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class BillViewModel @Inject constructor(
    private val billUseCases: BillUseCases
) : ViewModel(), BaseLogic<BillEvents>, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    private var _viewModelBill: MutableState<Bill?> = mutableStateOf(null)
    internal val viewModelBill get() = _viewModelBill
    private var deletedBill: Bill? = null

    internal val billTitle = mutableStateOf(_viewModelBill.value?.title ?: "")
    internal val billAmount = mutableStateOf("${_viewModelBill.value?.amount ?: ""}")
    internal val billDueDate = _viewModelBill.value?.dueDate ?: Calendar.getInstance()
    internal val billIcon = mutableStateOf(_viewModelBill.value?.icon ?: BillIcon.Position)
    internal val billIsPaid = mutableStateOf(_viewModelBill.value?.isPaid ?: false)
    internal val billArchived = mutableStateOf(_viewModelBill.value?.isArchived ?: false)

    private var billId: Int? = null

    private val _billsState = mutableStateOf(BillsState())
    internal val billsState: State<BillsState> = _billsState

    private val _eventFlow = MutableSharedFlow<BudgetLyContainer>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        billUseCases.getBills().onEach { bills ->
            _billsState.value = billsState.value.copy(
                bills = bills
            )
        }.launchIn(viewModelScope)
    }

    override fun onEvent(event: BillEvents) {
        when (event) {
            is BillEvents.ChangeBillIcon -> changeBillIcon(event.icon)
            is BillEvents.ChangeIsArchived -> changeIsArchived(event.isArchived)
            is BillEvents.ChangeIsPaid -> changeIsPaid(event.isPaid)
            is BillEvents.EnteredAmount -> enteredAmount(event.amount)
            is BillEvents.EnteredDueDate -> enteredDueDate(event.year, event.month, event.day)
            is BillEvents.EnteredTitle -> enteredTitle(event.billTitle)
            is BillEvents.DeleteBill -> deleteBill(event.bill)
            is BillEvents.EditBill -> editBill(event.bill)
            is BillEvents.BillSelected -> selectedBill(event.bill)
            BillEvents.SaveBill -> saveBill()
            BillEvents.UpdateBill -> updateBill()
            BillEvents.RestoreBill -> restoreBill()
            BillEvents.CancelNewBill -> cancelBill()
        }
    }

    private fun selectedBill(bill: Bill) {
        _viewModelBill.value = bill
    }

    private fun cancelBill() {

        billTitle.value = ""
        billAmount.value = ""
        billIcon.value = BillIcon.Position
        billIsPaid.value = false
        billArchived.value = false
        billDueDate.toInstant()
        billId = null

    }

    // i don't like this fun
    private fun editBill(bill: Bill){
        billTitle.value = bill.title
        billAmount.value = bill.amount.toString()
        billDueDate.set(
            bill.dueDate.get(Calendar.YEAR),
            bill.dueDate.get(Calendar.MONTH),
            bill.dueDate.get(Calendar.DAY_OF_MONTH)
        )
        billIcon.value = bill.icon
        billIsPaid.value = bill.isPaid
        billArchived.value = bill.isArchived
        billId = bill.id
    }

    private fun restoreBill() {
        launch{
            billUseCases.createBill(deletedBill!!)
            deletedBill = null
        }
    }
    private fun deleteBill(bill: Bill) = viewModelScope.launch {
        deletedBill = bill
        billUseCases.removeBill(bill = bill)
        _eventFlow.emit(BudgetLyContainer.ShowRestoreSnackbar(Resources.getSystem().getString(R.string.bill_deleted)))
        _viewModelBill.value = null
    }


    private fun updateBill() = viewModelScope.launch {
        _viewModelBill.value = Bill(
            title = billTitle.value,
            amount = billAmount.value.toFloat(),
            dueDate = billDueDate,
            icon = billIcon.value,
            isPaid = billIsPaid.value,
            isArchived = billArchived.value
        )
        try {
            billUseCases.updateBill(bill = _viewModelBill.value!!)
            _eventFlow.emit(BudgetLyContainer.ShowSuccess)
        } catch (e: InvalidBillException) {
            _eventFlow.emit(
                BudgetLyContainer
                    .ShowError(message = e.message ?: Resources.getSystem().getString(R.string.failed_to_update_bill))
            )
        }
        cancelBill()
    }

    private fun saveBill() = launch {
        _viewModelBill.value = Bill(
            title = billTitle.value,
            amount = billAmount.value.toFloat(),
            dueDate = billDueDate,
            icon = billIcon.value,
            isPaid = billIsPaid.value,
            isArchived = billArchived.value,
            id = billId
        )
        try {
            billUseCases.createBill(bill = _viewModelBill.value!!)
            _eventFlow.emit(BudgetLyContainer.ShowSuccess)
        } catch (e: InvalidBillException) {
            _eventFlow.emit(
                BudgetLyContainer
                    .ShowError(message = e.message ?: Resources.getSystem().getString(R.string.couldnt_save_bill))
            )
        }
        cancelBill()
    }

    private fun enteredTitle(title: String) {
        billTitle.value = title
    }

    private fun enteredDueDate(year: Int, month: Int, day: Int) {
        billDueDate.set(year, month, day)
    }

    private fun enteredAmount(amount: String) {
        billAmount.value = amount
    }

    private fun changeIsPaid(isPaid: Boolean) {
        billIsPaid.value = !isPaid
    }

    private fun changeIsArchived(archived: Boolean) {
        billArchived.value = !archived
        updateBill()
    }

    private fun changeBillIcon(icon: Int) {
        billIcon.value = icon
    }
}