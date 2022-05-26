package com.rick.data_transactions.domain

import kotlinx.coroutines.flow.Flow

class GetTransactionById(
    private val repository: ITransactionRepository
) {
    operator fun invoke(transactionId: Int): Flow<Transaction> =
        repository.getTransactionById(transactionId)
}