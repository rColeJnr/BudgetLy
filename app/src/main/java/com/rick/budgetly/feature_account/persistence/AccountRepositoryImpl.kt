package com.rick.budgetly.feature_account.persistence

import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.IAccountRepository

class AccountRepositoryImpl(
    private val accountDao: AccountDao
): IAccountRepository {

    override suspend fun saveAccount(account: Account) {
        accountDao.saveAccount(account)
    }

    override suspend fun deleteAccount(account: Account) {
        accountDao.deleteAccount(account)
    }

    override suspend fun getAccounts() = accountDao.getAccounts()

    override suspend fun getAccountById(id: Int) = accountDao.getAccountById(id)

    override suspend fun getAccountByType(type: String) = accountDao.getAccountByType(type)

}