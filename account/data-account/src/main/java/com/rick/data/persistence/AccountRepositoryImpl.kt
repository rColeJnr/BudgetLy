package com.rick.data.persistence

import com.rick.data.Account
import com.rick.data.IAccountRepository

class AccountRepositoryImpl(
    private val accountDao: AccountDao
): IAccountRepository {

    override suspend fun saveAccount(account: Account) {
        accountDao.saveAccount(account)
    }

    override suspend fun deleteAccount(account: Account) {
        accountDao.deleteAccount(account)
    }

    override fun getAccounts() = accountDao.getAccounts()

    override suspend fun getAccountById(id: Int) = accountDao.getAccountById(id)

    override suspend fun getAccountByType(type: String) = accountDao.getAccountByType(type)

}