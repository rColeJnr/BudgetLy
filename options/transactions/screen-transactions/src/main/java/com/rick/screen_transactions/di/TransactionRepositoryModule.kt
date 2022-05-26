package com.rick.screen_transactions.di

import com.rick.budgetly.feature_options.transactions.domain.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object TransactionRepositoryModule {

    @Singleton
    @Provides
    fun provideTransactionUseCase(repository: com.rick.data_transactions.domain.ITransactionRepository): com.rick.data_transactions.domain.TransactionUseCases =
        com.rick.data_transactions.domain.TransactionUseCases(
            com.rick.data_transactions.domain.GetTransactions(repository),
            com.rick.data_transactions.domain.GetTransactionById(repository),
            com.rick.data_transactions.domain.GetTransactionsByType(repository),
            com.rick.data_transactions.domain.SaveTransaction(repository),
            com.rick.data_transactions.domain.DeleteTransaction(repository)
        )

}