package com.rick.budgetly.feature_bills

import android.content.Context
import androidx.room.Room
import com.rick.bill_data.domain.IBillRepository
import com.rick.bill_data.BillDatabase
import com.rick.bill_data.BillRepositoryImpl
import com.rick.bill_data.util.Constants.BILL_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object BillsDatabaseModule {

    @Singleton
    @Provides
    fun providesBillDatabase(@ApplicationContext context: Context): BillDatabase =
        Room.databaseBuilder(
            context,
            BillDatabase::class.java,
            BILL_DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideBillRepository(billDatabase: BillDatabase): IBillRepository =
        BillRepositoryImpl(billDatabase.billDao)

}