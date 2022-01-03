package com.rick.budgetly.feature_options.transactions.persistence

import com.rick.budgetly.feature_options.transactions.domain.ITransactionRepository
import com.rick.budgetly.feature_options.transactions.domain.Transaction
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl(
    private val transactionDao: TransactionDao
): ITransactionRepository {

    override fun getTransactions(): Flow<List<Transaction>> {
        return transactionDao.getTransactions()
    }

    override fun getTransactionById(transactionId: Int): Flow<Transaction> {
        return transactionDao.getTransactionById(transactionId)
    }

    override fun getTransactionsByType(type: String): Flow<List<Transaction>> {
        return transactionDao.getTransactionsByType(type)
    }

    override suspend fun saveTransaction(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    override suspend fun deleteTransacton(transaction: Transaction) {
        transactionDao.delete(transaction)
    }
}