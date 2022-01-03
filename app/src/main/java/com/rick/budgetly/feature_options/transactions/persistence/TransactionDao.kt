package com.rick.budgetly.feature_options.transactions.persistence

import androidx.room.*
import com.rick.budgetly.feature_options.transactions.domain.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM `transaction` ORDER BY date")
    fun getTransactions(): Flow<List<Transaction>>

    @Query("SELECT * FROM `transaction` WHERE id = :id")
    fun getTransactionById(id: Int): Flow<Transaction>

    @Query("SELECT * FROM `transaction` WHERE type = :type ORDER BY date")
    fun getTransactionsByType(type: String): Flow<List<Transaction>>

    @Insert
    suspend fun insert(transaction: Transaction)

    @Delete
    suspend fun delete(transaction: Transaction)
}