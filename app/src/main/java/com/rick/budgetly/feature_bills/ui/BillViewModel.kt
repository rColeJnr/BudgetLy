package com.rick.budgetly.feature_bills.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rick.budgetly.BudgetLyContainer
import com.rick.budgetly.feature_account.common.BaseLogic
import com.rick.budgetly.feature_bills.domain.Bill
import com.rick.budgetly.feature_bills.domain.BillIcon
import com.rick.budgetly.feature_bills.domain.InvalidBillException
import com.rick.budgetly.feature_bills.domain.use_case.BillUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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

    internal var viewModelBill: MutableState<Bill?> = mutableStateOf(null)
    private var deletedBill: Bill? = null

    internal val billTitle = mutableStateOf(viewModelBill.value?.title ?: "")
    internal val billAmount = mutableStateOf("${viewModelBill.value?.amount ?: ""}")
    internal val billDueDate = viewModelBill.value?.dueDate ?: Calendar.getInstance()
    internal val billIcon = mutableStateOf(viewModelBill.value?.icon ?: BillIcon.Position)
    internal val billIsPaid = mutableStateOf(viewModelBill.value?.isPaid ?: false)
    internal val billArchived = mutableStateOf(viewModelBill.value?.isArchived ?: false)

    private val _billsState = mutableStateOf(BillsState())
    internal val billsState: State<BillsState> = _billsState

    private val _eventFlow = MutableSharedFlow<BudgetLyContainer>()
    val eventFlow = _eventFlow.asSharedFlow()

    val snackbarMessage = mutableStateOf("")

    val showSnackbar = mutableStateOf(false)


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
            BillEvents.SaveBill -> saveBill()
            BillEvents.UpdateBill -> updateBill()
            BillEvents.RestoreBill -> restoreBill()
            BillEvents.CancelNewBill -> cancelBill()
        }
    }

    private fun cancelBill() {

        billTitle.value = ""
        billAmount.value = ""
        billIcon.value = BillIcon.Position
        billIsPaid.value = false
        billArchived.value = false
        billDueDate.toInstant()

    }

    private fun restoreBill() {
        launch{
            billUseCases.createBill(deletedBill!!)
            deletedBill = null
        }
    }
    private fun deleteBill(bill: Bill) = viewModelScope.launch {
        launch {
            deletedBill = bill
            billUseCases.removeBill(bill = bill)
            _eventFlow.emit(BudgetLyContainer.ShowRestoreSnackbar("bill deleted"))

            // delay time to display snackbar with restore option
            delay(250)
        }.join()
        showSnackbar.value = false
    }


    private fun updateBill() = viewModelScope.launch {
        viewModelBill.value = Bill(
            title = billTitle.value,
            amount = billAmount.value.toFloat(),
            dueDate = billDueDate,
            icon = billIcon.value,
            isPaid = billIsPaid.value,
            isArchived = billArchived.value
        )
        try {
            billUseCases.updateBill(bill = viewModelBill.value!!)
            _eventFlow.emit(BudgetLyContainer.ShowSuccess)
        } catch (e: InvalidBillException) {
            _eventFlow.emit(
                BudgetLyContainer
                    .ShowError(message = e.message ?: "Couldn't update bill")
            )
        }
        cancelBill()
    }

    private fun saveBill() = launch {
        viewModelBill.value = Bill(
            title = billTitle.value,
            amount = billAmount.value.toFloat(),
            dueDate = billDueDate,
            icon = billIcon.value,
            isPaid = billIsPaid.value,
            isArchived = billArchived.value
        )
        try {
            billUseCases.createBill(bill = viewModelBill.value!!)
            _eventFlow.emit(BudgetLyContainer.ShowSuccess)
        } catch (e: InvalidBillException) {
            _eventFlow.emit(
                BudgetLyContainer
                    .ShowError(message = e.message ?: "Couldn't create bill")
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
    }

    private fun changeBillIcon(icon: Int) {
        billIcon.value = icon
    }
}