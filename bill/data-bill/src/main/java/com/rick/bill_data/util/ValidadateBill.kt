package com.rick.bill_data.util

import com.rick.bill_data.domain.Bill
import com.rick.bill_data.domain.InvalidBillException

fun validateBill(bill: Bill) {
    if (bill.title.isEmpty()) throw InvalidBillException("The name of the bill can't be empty")
    if (bill.amount <= 0) throw InvalidBillException("The amount of the bill can't be less or equal to zero")
}