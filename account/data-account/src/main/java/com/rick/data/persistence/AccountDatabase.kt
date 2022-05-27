package com.rick.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rick.data.Account

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