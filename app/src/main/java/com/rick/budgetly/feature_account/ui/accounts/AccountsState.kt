package com.rick.budgetly.feature_account.ui.accounts

import com.rick.budgetly.feature_account.domain.Account

data class AccountsState(
    val accounts: List<Account> = emptyList(),
    val accountsByType: List<Account> = emptyList(),
    val isAccountTypeVisible: Boolean = false
)
