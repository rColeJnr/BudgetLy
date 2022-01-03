package com.rick.budgetly.feature_bills.util

import com.rick.budgetly.feature_bills.domain.Bill
import com.rick.budgetly.feature_bills.domain.InvalidBillException

fun validateBill(bill: Bill) {
    if (bill.title.isEmpty()) throw InvalidBillException("The name of the bill can't be empty")
    if (bill.dueDate < 0) throw InvalidBillException("Invalid due date")
}