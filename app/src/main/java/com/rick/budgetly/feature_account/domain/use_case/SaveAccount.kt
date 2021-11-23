package com.rick.budgetly.feature_account.domain.use_case

import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.IAccountRepository
import com.rick.budgetly.feature_account.domain.InvalidAccountException

class SaveAccount(
    private val repository: IAccountRepository
) {

    suspend operator fun invoke(account: Account){
        if (account.title.isEmpty()){
            throw InvalidAccountException("The name of the account can't be empty")
        }
        if (account.balance.toFloat() > account.limit.toFloat()){
            throw InvalidAccountException("The account balance exceeded the set limit")
        }
        repository.saveAccount(account)
    }

}