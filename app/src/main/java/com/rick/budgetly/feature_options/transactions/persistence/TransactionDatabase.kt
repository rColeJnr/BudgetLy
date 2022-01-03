package com.rick.budgetly.feature_options.transactions.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rick.budgetly.feature_options.transactions.domain.Transaction

@Database(entities = [Transaction::class], version = 1, exportSchema = false)
@TypeConverters(TransactionConverters::class)
abstract class TransactionDatabase: RoomDatabase() {

    abstract val transactionDao: TransactionDao

}