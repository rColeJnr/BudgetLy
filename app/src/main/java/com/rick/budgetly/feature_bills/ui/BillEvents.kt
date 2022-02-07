package com.rick.budgetly.feature_bills.ui

import com.rick.budgetly.feature_bills.domain.Bill
import java.util.*

sealed class BillEvents {

    data class EnteredTitle(val billTitle: String): BillEvents()
    data class EnteredDueDate(val year: Int, val month: Int, val day: Int): BillEvents()
    data class EnteredAmount(val amount: String): BillEvents()
    data class ChangeBillIcon(val icon: Int): BillEvents()
    data class ChangeIsPaid(val isPaid: Boolean): BillEvents()
    data class ChangeIsArchived(val isArchived: Boolean): BillEvents()
    data class DeleteBill(val bill: Bill): BillEvents()
    object SaveBill: BillEvents()
    object UpdateBill: BillEvents()
    object RestoreBill: BillEvents()
    object CancelNewBill: BillEvents()

}