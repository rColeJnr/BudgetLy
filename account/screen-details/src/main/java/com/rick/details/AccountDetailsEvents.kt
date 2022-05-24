package com.rick.budgetly.feature_account.ui.accountdetails

import com.rick.budgetly.feature_account.domain.Account

sealed class AccountDetailsEvents{
    object ChangeIncludeInTotalStatus: AccountDetailsEvents()
    object ChangeMainStatus: AccountDetailsEvents()
    object RestoreAccount: AccountDetailsEvents()
    data class DeleteAccount(val account: Account): AccountDetailsEvents()
}
