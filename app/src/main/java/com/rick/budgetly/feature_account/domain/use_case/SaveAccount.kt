package com.rick.budgetly.feature_account.domain.use_case

import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.IAccountRepository
import com.rick.budgetly.feature_account.domain.InvalidAccountException
import com.rick.budgetly.feature_account.ui.accounts.AccountsContainer

class SaveAccount(
    private val repository: IAccountRepository,
    private val container: AccountsContainer?
) {

    suspend operator fun invoke(account: Account){
        if (account.name.isEmpty()){
            throw InvalidAccountException("The name of the account can't be empty")
        }
        repository.saveAccount(account)
    }

}