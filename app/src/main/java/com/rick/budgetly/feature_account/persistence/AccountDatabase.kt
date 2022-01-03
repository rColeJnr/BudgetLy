package com.rick.budgetly.feature_account.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rick.budgetly.feature_account.domain.Account

@Database(
    entities = [Account::class],
    version = 1
)
abstract class AccountDatabase: RoomDatabase() {

    abstract val accountDao: AccountDao

    companion object {
        const val ACCOUNT_DATABASE_NAME = "ACCOUNTOFDB"
    }

}