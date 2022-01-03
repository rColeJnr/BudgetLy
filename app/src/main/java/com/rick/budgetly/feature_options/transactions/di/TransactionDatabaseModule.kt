package com.rick.budgetly.feature_options.transactions.di

import android.content.Context
import androidx.room.Room
import com.rick.budgetly.feature_options.transactions.domain.ITransactionRepository
import com.rick.budgetly.feature_options.transactions.persistence.TransactionDatabase
import com.rick.budgetly.feature_options.transactions.persistence.TransactionRepositoryImpl
import com.rick.budgetly.feature_options.transactions.util.TRANSACTION_DATABASE_NAME
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
    fun provideTransactionDatabase(@ApplicationContext context: Context): TransactionDatabase =
        Room.databaseBuilder(
            context,
            TransactionDatabase::class.java,
            TRANSACTION_DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideTransactionRepository(transactionDatabase: TransactionDatabase): ITransactionRepository =
        TransactionRepositoryImpl(transactionDatabase.transactionDao)

}