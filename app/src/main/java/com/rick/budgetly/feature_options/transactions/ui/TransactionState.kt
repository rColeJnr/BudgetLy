package com.rick.budgetly.feature_options.transactions.ui

import java.time.LocalDateTime

data class TransactionState(
    val fromAToB: String = "",
    val data: LocalDateTime,
    val amount: Float
)

