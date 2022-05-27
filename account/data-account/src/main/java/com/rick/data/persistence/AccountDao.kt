package com.rick.data.persistence

import androidx.room.*
import com.rick.data.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    // i hope i come back to this, but i do think this is over engineered
    @Query("SELECT * FROM account")
    fun getAccounts(): Flow<List<Account>>

    @Query("SELECT * FROM account WHERE id = :id")
    fun getAccountById(id: Int) : Account?

    @Query("SELECT * FROM account WHERE type = :type")
    fun getAccountByType(type: String) : Flow<List<Account>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAccount(account: Account)

    @Delete
    suspend fun deleteAccount(account: Account)
}