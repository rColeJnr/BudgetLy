package com.rick.data_transactions.domain

data class TransactionUseCases(
    val getTransactions: GetTransactions,
    val getTransactionById: GetTransactionById,
    val getTransactionsByType: GetTransactionsByType,
    val saveTransaction: SaveTransaction,
    val deleteTransaction: DeleteTransaction
)
