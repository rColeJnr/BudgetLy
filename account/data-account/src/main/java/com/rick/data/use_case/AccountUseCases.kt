package com.rick.budgetly.feature_account.domain.use_case

data class AccountUseCases(
    val getAccounts: GetAccounts,
    val getAccountsByType: GetAccountByType,
    val getAccountById: GetAccountById,
    val saveAccount: SaveAccount,
    val deleteAccount: DeleteAccount

)
