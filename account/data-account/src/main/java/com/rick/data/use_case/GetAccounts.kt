package com.rick.data.use_case

import com.rick.data.Account
import com.rick.data.IAccountRepository
import kotlinx.coroutines.flow.Flow

class GetAccounts(
    private val repository: IAccountRepository
) {

    operator fun invoke(): Flow<List<Account>> {
        return repository.getAccounts()
    }

}

class GetAccountById(
    private val repository: IAccountRepository
) {
    suspend operator fun invoke(id: Int): Account? {
        return repository.getAccountById(id)
    }
}

// This doesn't work, it's not well done, but i'll get to it when we write room db tests
class GetAccountByType(
    private val repository: IAccountRepository
) {
    suspend operator fun invoke(type: String): Flow<List<Account>> {
        return repository.getAccountByType(type)
    }
}