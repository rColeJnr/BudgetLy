package com.rick.budgetly.feature_bills.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rick.budgetly.feature_account.common.BaseLogic
import com.rick.budgetly.feature_bills.domain.Bill
import com.rick.budgetly.feature_bills.domain.BillIcon
import com.rick.budgetly.feature_bills.domain.use_case.BillUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class BillViewModel @Inject constructor(
    private val billUseCases: BillUseCases
): ViewModel(), BaseLogic<BillEvents>, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    private var viewModelBill: Bill? = null
    private var deletedBill: Bill? = null

    internal val billTitle = mutableStateOf("")
    internal val billAmount = mutableStateOf("")
    internal val billDueDate = Calendar.getInstance()
    internal val billIcon = mutableStateOf(BillIcon.Position)
    internal val billIsPaid = mutableStateOf(false)
    internal val billArchived = mutableStateOf(false)

    private val _billsState = mutableStateOf(BillsState())
    internal val billsState: State<BillsState> = _billsState

    init {
        billUseCases.getBills().onEach { bills ->
            _billsState.value = billsState.value.copy(
                bills = bills
            )
        }.launchIn(viewModelScope)
    }

    override fun onEvent(event: BillEvents) {
        when(event){
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

    private fun cancelBill(){

        billTitle.value = ""
        billAmount.value = ""
        billIcon.value = BillIcon.Position
        billIsPaid.value = false
        billArchived.value = false

    }

    private fun restoreBill() {
        launch {
            billUseCases.createBill( deletedBill!!)
            deletedBill = null
        }
    }

    private fun deleteBill(bill: Bill) {
        launch {
            deletedBill = bill
            billUseCases.removeBill(bill = bill)
        }
    }

    private fun updateBill(){
        viewModelBill = Bill(
            title = billTitle.value,
            amount = billAmount.value.toFloat(),
            dueDate = Calendar.getInstance().apply { timeInMillis = 457195L },
            icon = billIcon.value,
            isPaid = billIsPaid.value,
            isArchived = billArchived.value
        )
        launch {
            billUseCases.updateBill(bill = viewModelBill!!)
        }
        cancelBill()
    }

    private fun saveBill() {
        viewModelBill = Bill(
            title = billTitle.value,
            amount = billAmount.value.toFloat(),
            dueDate = Calendar.getInstance().apply { timeInMillis = 457195L },
            icon = billIcon.value,
            isPaid = billIsPaid.value,
            isArchived = billArchived.value
        )
        launch {
            billUseCases.createBill(bill = viewModelBill!!)
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
        billIsPaid.value = isPaid
    }

    private fun changeIsArchived(archived: Boolean) {
        billArchived.value = archived
    }

    private fun changeBillIcon(icon: Int) {
        billIcon.value = icon
    }
}