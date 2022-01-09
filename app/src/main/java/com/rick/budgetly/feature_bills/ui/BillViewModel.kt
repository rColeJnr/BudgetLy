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

    var currentBill: Bill? = null
    private var deletedBill: Bill? = null

    internal val billTitle = mutableStateOf("")
    internal val billAmount = mutableStateOf(0f)
    internal val billDueDate = mutableStateOf(Calendar.getInstance())
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
            is BillEvents.EnteredDueDate -> enteredDueDate(event.dueDate)
            is BillEvents.EnteredTitle -> enteredTitle(event.billTitle)
            is BillEvents.BillSelected -> billSelected(event.bill)
            is BillEvents.DeleteBill -> deleteBill(event.bill)
            BillEvents.SaveBill -> saveBill()
            BillEvents.UpdateBill -> updateBill()
            BillEvents.RestoreBill -> restoreBill()
        }
    }

    private fun restoreBill() {
        launch {
            billUseCases.createBill( deletedBill ?: return@launch )
            deletedBill = null
        }
    }

    private fun billSelected(bill: Bill) {
// Unnecessary
    }

    private fun deleteBill(bill: Bill) {
        launch {
            deletedBill = bill
        }
    }

    private fun updateBill(){
        currentBill = Bill(
            title = billTitle.value,
            amount = billAmount.value,
            dueDate = billDueDate.value,
            icon = billIcon.value,
            isPaid = billIsPaid.value,
            isArchived = billArchived.value
        )
        launch {
            billUseCases.updateBill(bill = currentBill!!)
        }
    }

    private fun saveBill() {
        currentBill = Bill(
            title = billTitle.value,
            amount = billAmount.value,
            dueDate = billDueDate.value,
            icon = billIcon.value,
            isPaid = billIsPaid.value,
            isArchived = billArchived.value
        )
        launch {
            billUseCases.createBill(bill = currentBill!!)
        }
    }

    private fun enteredTitle(title: String) {
        billTitle.value = title
    }

    private fun enteredDueDate(dueData: Long) {
        val date = Calendar.getInstance().apply { timeInMillis = dueData }
        billDueDate.value = date
    }

    private fun enteredAmount(amount: Float) {
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