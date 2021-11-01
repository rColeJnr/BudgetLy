package com.rick.budgetly.feature_account.ui.accounts

import com.rick.budgetly.feature_account.domain.Account

data class AccountsState(
    val accounts: List<Account> = emptyList(),
    val accountsByTypeCash: List<Account> = emptyList(),
    val accountsByTypeDebts: List<Account> = emptyList(),
    val accountsByTypeLoans: List<Account> = emptyList(),
    val accountsByTypeSavings: List<Account> = emptyList(),
    val isAccountTypeVisible: HashMap<String, Boolean> = hashMapOf(
        AccountType.CASH.type to true,
        AccountType.DEBTS.type to false,
        AccountType.LOANS.type to false,
        AccountType.SAVINGS.type to false,
    )
)
enum class AccountType(val type: String) {
    CASH("CASH"), DEBTS("DEBTS"), LOANS("LOANS"), SAVINGS("SAVINGS")
}