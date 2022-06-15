package com.rick.data.use_case

import com.rick.data.Account
import com.rick.data.IAccountRepository

class DeleteAccount(
    private val repository: IAccountRepository
) {
    suspend operator fun invoke(account: Account){
        repository.deleteAccount(account)
    }

}