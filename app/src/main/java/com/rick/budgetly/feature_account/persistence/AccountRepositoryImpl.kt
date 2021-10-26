package com.rick.budgetly.feature_account.persistence

import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.IAccountRepository

class AccountRepositoryImpl(
    private val accountDao: AccountDao
): IAccountRepository {

    override suspend fun saveAccount(
        account: Account,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when (val saveAccountResult: AccountDaoResult = accountDao.saveAccount(account)){
            is AccountDaoResult.OnError -> onError(saveAccountResult.exception)
            is AccountDaoResult.OnSuccess -> onSuccess(Unit)
        }
    }

    override suspend fun deleteAccount(
        account: Account,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when (val deleteAccountResult: AccountDaoResult = accountDao.deleteAccount(account)){
            is AccountDaoResult.OnError -> onError(deleteAccountResult.exception)
            is AccountDaoResult.OnSuccess -> onSuccess(Unit)
        }
    }

    override suspend fun getAccounts(
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when (val getAccountDaoResult: AccountDaoResult = accountDao.getAccounts()){
            is AccountDaoResult.OnError -> onError(getAccountDaoResult.exception)
            is AccountDaoResult.OnSuccess -> Unit
            is AccountDaoResult.OnSuccessFlow -> getAccountDaoResult.accounts
        }
    }

    override suspend fun getAccountById(
        id: Int,
        onSuccess: (account: Account) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when (val getAccountByIdResult = accountDao.getAccountById(id)){
            is AccountDaoResult.OnError -> onError(getAccountByIdResult.exception)
            is AccountDaoResult.OnSuccess -> getAccountByIdResult.account
        }
    }

    override suspend fun getAccountByType(
        type: String,
        onSuccess: (account: Account) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when (val getAccountByType = accountDao.getAccountByType(type)){
            is AccountDaoResult.OnError -> onError(getAccountByType.exception)
            is AccountDaoResult.OnSuccessFlow -> getAccountByType.accounts
        }
    }
}