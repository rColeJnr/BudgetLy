package com.rick.budgetly.feature_account.ui.accounts

import com.rick.budgetly.feature_account.domain.Account

sealed class AccountEvents{

    data class DeleteAccount(val account: Account): AccountEvents()
    object RestoreAccount: AccountEvents()
    object ToggleAccount: AccountEvents()
    object OnStart: AccountEvents()
    object OnStop: AccountEvents()
    data class ToggleAccountType(val type: String): AccountEvents()

}
