package com.rick.budgetly.feature_account.domain

import kotlinx.coroutines.flow.Flow

interface IAccountRepository {

    suspend fun saveAccount(account: Account)

    suspend fun deleteAccount(account: Account)

    suspend fun getAccounts(): Flow<List<Account>>

    suspend fun getAccountById(id: Int): Account?

    suspend fun getAccountByType(type: String): Flow<List<Account>>

    // I feel like get account by currency is a bit extra
//    suspend fun getAccountByCurrency(
//        onSuccess: (accountCurrency: String) -> Account?,
//        onError: (Exception) -> Unit
//    )
}