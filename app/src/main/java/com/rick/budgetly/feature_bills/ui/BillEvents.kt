package com.rick.budgetly.feature_bills.ui

import com.rick.budgetly.feature_bills.domain.Bill

sealed class BillEvents {

    data class EnteredTitle(val billTitle: String): BillEvents()
    data class EnteredDueDate(val dueDate: Long): BillEvents()
    data class EnteredAmount(val amount: Float): BillEvents()
    data class ChangeBillIcon(val icon: Int): BillEvents()
    data class ChangeIsPaid(val isPaid: Boolean): BillEvents()
    data class ChangeIsArchived(val isArchived: Boolean): BillEvents()
    data class BillSelected(val bill: Bill): BillEvents()
    data class DeleteBill(val bill: Bill): BillEvents()
    object SaveBill: BillEvents()
    object UpdateBill: BillEvents()
    object RestoreBill: BillEvents()

}