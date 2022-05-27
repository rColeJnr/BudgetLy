package com.rick.details

import android.accounts.Account

sealed class AccountDetailsEvents{
    object ChangeIncludeInTotalStatus: AccountDetailsEvents()
    object ChangeMainStatus: AccountDetailsEvents()
    object RestoreAccount: AccountDetailsEvents()
    data class DeleteAccount(val account: Account): AccountDetailsEvents()
}
