package com.rick.budgetly.feature_options.transactions.domain

class SaveTransaction(
    private val repository: ITransactionRepository
) {
    suspend operator fun invoke(transaction: Transaction){
        repository.saveTransaction(transaction)
    }
}