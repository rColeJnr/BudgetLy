package com.rick.budgetly.feature_account.ui.accountdetails

import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.ui.accounts.AccountEvents

sealed class AccountDetailsEvents{
    data class ChangeBalance(val balance: String): AccountDetailsEvents()
    data class ChangeIncludeInTotalStatus(val include: Boolean): AccountDetailsEvents()
    data class DeleteAccount(val account: Account): AccountDetailsEvents()
    object RestoreAccount: AccountDetailsEvents()
    object SaveChanges: AccountDetailsEvents()
}
