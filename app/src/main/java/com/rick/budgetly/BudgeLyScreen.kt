package com.rick.budgetly

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class BudgetLyScreen(val icon: ImageVector) {
    Accounts (icon = Icons.Default.AttachMoney),
    Transactions (icon = Icons.Default.History),
    Overview (icon = Icons.Default.PieChart);
//    TODO categories,
//    TODO Overview

    companion object {
        // this keeps track of  the backstack
        fun fromRoute(route: String?): BudgetLyScreen =
            when (route?.substringBefore("/")){
                Accounts.name -> Accounts
                Transactions.name -> Transactions
                Overview.name -> Overview
                null -> Accounts
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}