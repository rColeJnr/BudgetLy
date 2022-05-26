package com.rick.budgetly.feature_bills.ui

import com.rick.bill_data.domain.Bill

data class BillsState(
    val bills: List<Bill> = emptyList()
)