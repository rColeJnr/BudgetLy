package com.rick.budgetly.feature_account

import android.app.Application
import androidx.room.Room
import com.rick.budgetly.feature_account.common.ProductionDispatcherProvider
import com.rick.budgetly.feature_account.domain.IAccountRepository
import com.rick.budgetly.feature_account.domain.IQuoteApi
import com.rick.budgetly.feature_account.domain.IQuoteRepository
import com.rick.budgetly.feature_account.domain.use_case.*
import com.rick.budgetly.feature_account.persistence.AccountDatabase
import com.rick.budgetly.feature_account.persistence.AccountDatabase.Companion.ACCOUNT_DATABASE_NAME
import com.rick.budgetly.feature_account.persistence.AccountRepositoryImpl
import com.rick.budgetly.feature_account.persistence.QuoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AccountModule {

    @Provides
    @Singleton
    fun providesAccountDatabase(app: Application): AccountDatabase =
        Room.databaseBuilder(
            app,
            AccountDatabase::class.java,
            ACCOUNT_DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun providesAccountRepository(database: AccountDatabase): IAccountRepository =
        AccountRepositoryImpl(database.accountDao)

    @Provides
    @Singleton
    fun providesAccountUseCases(repository: IAccountRepository): AccountUseCases  = AccountUseCases(
        getAccounts = GetAccounts(repository),
        getAccountsByType = GetAccountByType(repository),
        getAccountById = GetAccountById(repository),
        saveAccount = SaveAccount(repository ),
        deleteAccount = DeleteAccount(repository)
    )

    @Provides
    @Singleton
    fun providesAccountDispatchers() =
        ProductionDispatcherProvider

    @Provides
    @Singleton
    fun quoteAPI(retrofit: Retrofit): IQuoteApi = retrofit.create(IQuoteApi::class.java)

    @Provides
    @Singleton
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.kanye.rest")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesQuoteRepository(api: IQuoteApi): IQuoteRepository{
        return QuoteRepositoryImpl(api)
    }
}