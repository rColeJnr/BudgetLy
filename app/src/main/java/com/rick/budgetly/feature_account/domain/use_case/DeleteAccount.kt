package com.rick.budgetly.feature_account.domain.use_case

import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.IAccountRepository
import com.rick.budgetly.feature_account.ui.accounts.AccountsContainer

class DeleteAccount(
    private val repository: IAccountRepository
) {
    suspend operator fun invoke(account: Account){
        repository.deleteAccount(account)
    }

}