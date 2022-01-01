package com.rick.budgetly.feature_bills.ui

sealed class BillEvents {

    data class EnteredTitle(val billTitle: String): BillEvents()
    data class EnteredDueDate(val dueDate: Int): BillEvents()
    data class EnteredAmount(val amount: Int): BillEvents()
    data class ChangeBillIcon(val icon: Int): BillEvents()
    data class ChangeIsPaid(val isPaid: Boolean): BillEvents()
    data class ChangeIsArchived(val isArchived: Boolean): BillEvents()
    object SaveBill: BillEvents()
    object DeleteBill: BillEvents()

}