package com.rick.budgetly.feature_options.transactions.domain

import kotlinx.coroutines.flow.Flow

class GetTransactionsByType(
    private val repository: ITransactionRepository
) {
    operator fun invoke(transactionType: String): Flow<List<Transaction>> =
        repository.getTransactionsByType(transactionType)
}