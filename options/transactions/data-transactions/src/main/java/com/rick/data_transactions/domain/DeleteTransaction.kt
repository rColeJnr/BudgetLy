package com.rick.data_transactions.domain

class DeleteTransaction(
    private val repository: ITransactionRepository
) {
    suspend operator fun invoke(transaction: Transaction){
        repository.deleteTransacton(transaction)
    }
}