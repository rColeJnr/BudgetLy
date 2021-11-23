package com.rick.budgetly.feature_account.ui.accountdetails

import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.ui.accounts.AccountEvents

sealed class AccountDetailsEvents{
    object ChangeIncludeInTotalStatus: AccountDetailsEvents()
    object ChangeMainStatus: AccountDetailsEvents()
    object RestoreAccount: AccountDetailsEvents()
    data class DeleteAccount(val account: Account): AccountDetailsEvents()
}
