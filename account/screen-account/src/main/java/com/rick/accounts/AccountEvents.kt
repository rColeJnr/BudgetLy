package com.rick.accounts

import com.rick.data.AccountType

sealed class AccountEvents{

    object ToggleAccount: AccountEvents()
    object OnStart: AccountEvents()
    object OnStop: AccountEvents()

    data class ToggleAccountType(val accountType: AccountType): AccountEvents()

}
