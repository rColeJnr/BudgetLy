package com.rick.budgetly.feature_account.domain

import kotlinx.coroutines.flow.Flow

interface IAccountRepository {

    suspend fun saveAccount(
        account: Account,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    )

    suspend fun deleteAccount(
        account: Account,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    )

    suspend fun getAccounts(
        onSuccess: (accounts: Flow<List<Account>>) -> Unit,
        onError: (Exception) -> Unit
    )

    suspend fun getAccountById(
        id: Int,
        onSuccess: (account: Account) -> Unit,
        onError: (Exception) -> Unit
    )

    suspend fun getAccountByType(
        type: String,
        onSuccess: (accounts: Flow<List<Account>>) -> Unit,
        onError: (Exception) -> Unit
    )

    // I feel like get account by currency is a bit extra
//    suspend fun getAccountByCurrency(
//        onSuccess: (accountCurrency: String) -> Account?,
//        onError: (Exception) -> Unit
//    )
}