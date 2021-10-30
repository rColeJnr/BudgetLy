package com.rick.budgetly.feature_account.ui.accounts

interface AccountsContainer{

    fun showError(message: String)
    fun showSuccess()
    fun onAccountClicked()

}