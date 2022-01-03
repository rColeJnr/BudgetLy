package com.rick.budgetly.feature_options.transactions.domain

import kotlinx.coroutines.flow.Flow

class GetTransactions(
    private val repository: ITransactionRepository
) {
    operator fun invoke(): Flow<List<Transaction>> =
        repository.getTransactions()
}