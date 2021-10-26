package com.rick.budgetly.feature_account.domain

interface IAccountRepository {

    suspend fun saveAccount(
        onSuccess: (account: Account) -> Unit,
        onError: (Exception) -> Unit
    )

    suspend fun getAccounts(
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    )

    suspend fun getAccountById(
        onSuccess: (id: Int) -> Account?,
        onError: (Exception) -> Unit
    )

    suspend fun getAccountByType(
        onSuccess: (accountType: String) -> Account?,
        onError: (Exception) -> Unit
    )

    // I feel like get account by currency is a bit extra
//    suspend fun getAccountByCurrency(
//        onSuccess: (accountCurrency: String) -> Account?,
//        onError: (Exception) -> Unit
//    )

    suspend fun deleteAccount(
        onSuccess: (account: Account) -> Unit,
        onError: (Exception) -> Unit
    )

}