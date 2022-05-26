package com.rick.overview

data class OverviewState (
    val totalMoney: Float = 0f, // sum of bills and accounts spends
    val categoriesToggle: Boolean = false
)
