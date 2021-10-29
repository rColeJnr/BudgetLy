package com.rick.budgetly.feature_account.ui.accounts

import com.rick.budgetly.feature_account.domain.Account

data class AccountsState(
    val accounts: List<Account> = emptyList(),
    val accountsByType: List<Account> = emptyList(),
    val isAccountTypeVisible: HashMap<String, Boolean> = hashMapOf(
        CASH to true,
        DEBIT to false,
        LOANS to false,
        SAVINGS to false,
    )
) {
    companion object {
        const val CASH = "CASH"
        const val DEBIT = "DEBIT"
        const val LOANS = "LOANS"
        const val SAVINGS = "SAVINGS"
    }
}