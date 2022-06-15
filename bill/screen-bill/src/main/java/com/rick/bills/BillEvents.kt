package com.rick.bills

import com.rick.bill_data.domain.Bill

sealed class BillEvents {

    data class EnteredTitle(val billTitle: String): BillEvents()
    data class EnteredDueDate(val date: Triple<Int, Int, Int>): BillEvents()
    data class EnteredAmount(val amount: String): BillEvents()
    data class ChangeBillIcon(val icon: Int): BillEvents()
    data class ChangeIsPaid(val isPaid: Boolean): BillEvents()
    data class ChangeIsArchived(val isArchived: Boolean): BillEvents()
    data class DeleteBill(val bill: Bill): BillEvents()
    data class EditBill(val bill: Bill): BillEvents()
    // TODO temporal soluction
    data class BillSelected(val bill: Bill): BillEvents()
    object SaveBill: BillEvents()
    object UpdateBill: BillEvents()
    object RestoreBill: BillEvents()
    object CancelNewBill: BillEvents()

}