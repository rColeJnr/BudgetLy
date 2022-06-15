package com.rick.details

import com.rick.data.Account

sealed class AccountDetailsEvents{
    object ChangeIncludeInTotalStatus: AccountDetailsEvents()
    object ChangeMainStatus: AccountDetailsEvents()
    object RestoreAccount: AccountDetailsEvents()
    data class DeleteAccount(val account: Account): AccountDetailsEvents()
}
