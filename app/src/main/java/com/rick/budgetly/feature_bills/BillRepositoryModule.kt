package com.rick.budgetly.feature_bills

import com.rick.budgetly.feature_bills.domain.IBillRepository
import com.rick.budgetly.feature_bills.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object BillRepositoryModule {

    @Provides
    @Singleton
    fun provideBillUseCases(repository: IBillRepository): BillUseCases =
        BillUseCases(
            GetBills(repository),
            GetBill(repository),
            CreateBill(repository),
            UpdateBill(repository),
            RemoveBill(repository)
        )

}