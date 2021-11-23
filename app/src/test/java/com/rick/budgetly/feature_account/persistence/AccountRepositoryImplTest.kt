package com.rick.budgetly.feature_account.persistence

import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.IAccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Assert.*

class AccountRepositoryImplTest: IAccountRepository{

    private val accounts = mutableListOf<Account>()

    override suspend fun saveAccount(account: Account) {
        accounts.add(account)
    }

    override suspend fun getAccountByType(type: String): Flow<List<Account>> {
        return flow { accounts.find { it.type == type } }
    }

    override suspend fun deleteAccount(account: Account) {
        accounts.remove(account)
    }

    override fun getAccounts(): Flow<List<Account>> {
        return flow { emit(accounts) }
    }

    override suspend fun getAccountById(id: Int): Account? {
        return accounts.find { it.id == id }
    }
}