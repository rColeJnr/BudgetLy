package com.rick.budgetly.feature_account.ui.accounts

sealed class AccountsContainer {

    data class ShowError(val message: String): AccountsContainer()
    object ShowSuccess: AccountsContainer()

}