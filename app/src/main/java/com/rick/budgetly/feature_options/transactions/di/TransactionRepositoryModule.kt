package com.rick.budgetly.feature_options.transactions.di

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
    fun provideTransactionUseCase(repository: ITransactionRepository): TransactionUseCases =
        TransactionUseCases(
            GetTransactions(repository),
            GetTransactionById(repository),
            GetTransactionsByType(repository),
            SaveTransaction(repository),
            DeleteTransaction(repository)
        )

}