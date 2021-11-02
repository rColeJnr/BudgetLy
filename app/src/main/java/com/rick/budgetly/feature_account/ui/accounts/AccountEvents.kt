package com.rick.budgetly.feature_account.ui.accounts

import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.AccountType

sealed class AccountEvents{

    object ToggleAccount: AccountEvents()
    object OnStart: AccountEvents()
    object OnStop: AccountEvents()

    data class ToggleAccountType(val accountType: AccountType): AccountEvents()

}
