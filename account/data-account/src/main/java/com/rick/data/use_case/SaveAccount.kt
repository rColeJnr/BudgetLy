package com.rick.data.use_case

import com.rick.data.Account
import com.rick.data.IAccountRepository
import com.rick.data.InvalidAccountException

class SaveAccount(
    private val repository: IAccountRepository
) {

    suspend operator fun invoke(account: Account) {
        if (account.title.isEmpty()) {
            throw InvalidAccountException("The name of the account can't be empty")
        }
        if (account.balance.isEmpty() || account.limit.isEmpty()) {
            throw InvalidAccountException("The account balance nor limit can't be left empty")
        } else {
            if (account.balance.toFloat() > account.limit.toFloat()) {
                throw InvalidAccountException("The account balance exceeded the set limit")
            }
        }

        repository.saveAccount(account)
    }

}