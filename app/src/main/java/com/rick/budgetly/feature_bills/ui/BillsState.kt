package com.rick.budgetly.feature_bills.ui

import com.rick.budgetly.feature_bills.domain.Bill

data class BillsState(
    val bills: List<Bill> = emptyList()
)