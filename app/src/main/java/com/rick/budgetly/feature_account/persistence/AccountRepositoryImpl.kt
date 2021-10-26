package com.rick.budgetly.feature_account.persistence

import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.IAccountRepository
import kotlinx.coroutines.flow.Flow

class AccountRepositoryImpl(
    private val accountDao: AccountDao
): IAccountRepository {

    override suspend fun saveAccount(
        account: Account,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    ) {
        accountDao.saveAccount(account)
    }

    override suspend fun deleteAccount(
        account: Account,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when (val deleteAccountResult: AccountDaoResult = ){
            is AccountDaoResult.OnError -> onError(deleteAccountResult.exception)
            is AccountDaoResult.OnSuccess -> onSuccess(Unit)
        }
    }

    override suspend fun getAccounts(
        onSuccess: (accounts: Flow<List<Account>>) -> Unit,
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
        onSuccess: (account: Flow<List<Account>>) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when (val getAccountByType = accountDao.getAccountByType(type)){
            is AccountDaoResult.OnError -> onError(getAccountByType.exception)
            is AccountDaoResult.OnSuccessFlow -> getAccountByType.accounts
        }
    }
}

sealed class AccountDaoResult {
    data class OnSuccessFlow(val accounts: Flow<List<Account>>): AccountDaoResult()
    data class OnSuccess(val account: Account): AccountDaoResult()
    data class OnError(val exception: InvalidAccountException): AccountDaoResult()
}


// Just realized that there are the use cases
// If necessary i will move the functions to use case files
// but i believe that won't be necessary