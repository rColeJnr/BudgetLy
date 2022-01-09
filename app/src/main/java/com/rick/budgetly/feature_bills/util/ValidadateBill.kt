package com.rick.budgetly.feature_bills.util

import com.rick.budgetly.feature_bills.domain.Bill
import com.rick.budgetly.feature_bills.domain.InvalidBillException
import java.util.*

fun validateBill(bill: Bill) {
    if (bill.title.isEmpty()) throw InvalidBillException("The name of the bill can't be empty")
    if (bill.amount <= 0) throw InvalidBillException("The amount of the bill can't be less or equal to zero")
}