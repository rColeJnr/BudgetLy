package com.rick.budgetly.feature_options.transactions.domain

data class TransactionUseCases(
    val getTransactions: GetTransactions,
    val getTransactionById: GetTransactionById,
    val getTransactionsByType: GetTransactionsByType,
    val saveTransaction: SaveTransaction,
    val deleteTransaction: DeleteTransaction
)
