package com.rick.budgetly.feature_account.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rick.budgetly.feature_account.domain.Account

@Database(
    entities = [Account::class],
    version = 69
)
abstract class AccountDatabase: RoomDatabase() {

    abstract val accountDao: AccountDao

    companion object {
        const val DATABASE_NAME = "ACCOUNTofDB"
    }

}