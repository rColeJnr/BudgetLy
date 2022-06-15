package com.rick.data

import kotlinx.coroutines.flow.Flow

interface IAccountRepository {

    suspend fun saveAccount(account: Account)

    suspend fun deleteAccount(account: Account)

    fun getAccounts(): Flow<List<Account>>

    suspend fun getAccountById(id: Int): Account?

    suspend fun getAccountByType(type: String) : Flow<List<Account>>

    // I feel like get account by currency is a bit extra
//    suspend fun getAccountByCurrency(
//        onSuccess: (accountCurrency: String) -> Account?,
//        onError: (Exception) -> Unit
//    )
}

//sealed class AccountRepositoryResult {
//    data class OnSuccessFlow(val accounts: Flow<List<Account>>): AccountRepositoryResult()
//    data class OnSuccess(val account: Account): AccountRepositoryResult()
//    data class OnError(val exception: InvalidAccountException): AccountRepositoryResult()
//}