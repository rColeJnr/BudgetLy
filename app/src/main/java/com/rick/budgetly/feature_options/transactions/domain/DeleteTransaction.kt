package com.rick.budgetly.feature_options.transactions.domain

class DeleteTransaction(
    private val repository: ITransactionRepository
) {
    suspend operator fun invoke(transaction: Transaction){
        repository.deleteTransacton(transaction)
    }
}