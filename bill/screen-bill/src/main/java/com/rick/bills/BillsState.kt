package com.rick.bills

import com.rick.bill_data.domain.Bill

data class BillsState(
    val bills: List<Bill> = emptyList()
)