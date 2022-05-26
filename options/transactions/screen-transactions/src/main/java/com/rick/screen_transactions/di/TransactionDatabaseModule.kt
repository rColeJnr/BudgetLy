package com.rick.screen_transactions.di

import android.content.Context
import androidx.room.Room
import com.rick.data_transactions.domain.ITransactionRepository
import com.rick.data_transactions.persistence.TransactionDatabase
import com.rick.data_transactions.persistence.TransactionRepositoryImpl
import com.rick.data_transactions.util.TRANSACTION_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object TransactionDatabaseModule {

    @Singleton
    @Provides
    fun provideTransactionDatabase(@ApplicationContext context: Context): com.rick.data_transactions.persistence.TransactionDatabase =
        Room.databaseBuilder(
            context,
            com.rick.data_transactions.persistence.TransactionDatabase::class.java,
            com.rick.data_transactions.util.TRANSACTION_DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideTransactionRepository(transactionDatabase: com.rick.data_transactions.persistence.TransactionDatabase): com.rick.data_transactions.domain.ITransactionRepository =
        com.rick.data_transactions.persistence.TransactionRepositoryImpl(transactionDatabase.transactionDao)

}