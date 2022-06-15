package com.rick.data_transactions.domain

import kotlinx.coroutines.flow.Flow

class GetTransactions(
    private val repository: ITransactionRepository
) {
    operator fun invoke(): Flow<List<Transaction>> =
        repository.getTransactions()
}