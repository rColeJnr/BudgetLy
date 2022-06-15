package com.rick.accounts

import com.rick.data.Account
import com.rick.data.AccountType

data class AccountsState(
    val accounts: List<Account> = emptyList(),
    val quote: String = "",
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