package com.rick.data_transactions.domain

import kotlinx.coroutines.flow.Flow

interface ITransactionRepository {

    fun getTransactions(): Flow<List<Transaction>>

    fun getTransactionById(transactionId: Int): Flow<Transaction>

    fun getTransactionsByType(type: String): Flow<List<Transaction>>

    suspend fun saveTransaction(transaction: Transaction)

    suspend fun deleteTransacton(transaction: Transaction)
}