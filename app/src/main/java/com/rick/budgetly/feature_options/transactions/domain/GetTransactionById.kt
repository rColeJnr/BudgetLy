package com.rick.budgetly.feature_options.transactions.domain

import kotlinx.coroutines.flow.Flow

class GetTransactionById(
    private val repository: ITransactionRepository
) {
    operator fun invoke(transactionId: Int): Flow<Transaction> =
        repository.getTransactionById(transactionId)
}