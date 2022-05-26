package com.rick.budgetly

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class BudgetLyScreen(val icon: ImageVector) {
    Accounts (icon = Icons.Default.AttachMoney),
    Bills (icon = Icons.Default.MoneyOff),
    Options (icon = Icons.Default.Settings);
//    TODO categories,
//    TODO Overview

    companion object {
        // this keeps track of  the backstack
        fun fromRoute(route: String?): BudgetLyScreen =
            when (route?.substringBefore("/")){
                Accounts.name -> Accounts
                Bills.name -> Bills
                Options.name -> Options
                null -> Accounts
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}