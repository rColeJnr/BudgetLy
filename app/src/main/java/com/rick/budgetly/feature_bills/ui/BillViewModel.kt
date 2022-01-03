package com.rick.budgetly.feature_bills.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.rick.budgetly.feature_account.common.BaseLogic
import com.rick.budgetly.feature_bills.domain.Bill

class BillViewModel: ViewModel(), BaseLogic<BillEvents> {

    var bill: Bill? = null

    internal val title = mutableStateOf("")
    internal val amount = mutableStateOf(0)
    internal val dueDate = mutableStateOf(0L)


    override fun onEvent(event: BillEvents) {
        when(event){
            is BillEvents.ChangeBillIcon -> changeBillIcon(event.icon)
            is BillEvents.ChangeIsArchived -> changeIsArchived(event.isArchived)
            is BillEvents.ChangeIsPaid -> changeIsPaid(event.isPaid)
            is BillEvents.EnteredAmount -> enteredAmount(event.amount)
            is BillEvents.EnteredDueDate -> enteredDueDate(event.dueDate)
            is BillEvents.EnteredTitle -> enteredTitle(event.billTitle)
            BillEvents.SaveBill -> saveBill()
            BillEvents.DeleteBill -> deleteBill()
        }
    }

    private fun deleteBill() {

    }

    private fun saveBill() {

    }

    private fun enteredTitle(billTitle: String) {

    }

    private fun enteredDueDate(dueDate: Int) {

    }

    private fun enteredAmount(amount: Int) {

    }

    private fun changeIsPaid(isPaid: Boolean) {

    }

    private fun changeIsArchived(archived: Boolean) {

    }

    private fun changeBillIcon(icon: Int) {

    }
}