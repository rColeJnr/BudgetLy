package com.rick.budgetly.feature_account.persistence

import androidx.room.*
import com.rick.budgetly.feature_account.domain.Account
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

@Dao
interface AccountDao {

    // i hope i come back to this, but i do think this is over engineered
    @Query("SELECT * FROM account")
    fun getAccounts(): AccountDaoResult

    @Query("SELECT * FROM account WHERE id = :id")
    fun getAccountById(id: Int) : AccountDaoResult

    @Query("SELECT * FROM account WHERE type = :type")
    fun getAccountByType(type: String) : AccountDaoResult

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAccount(account: Account) : AccountDaoResult

    @Delete
    suspend fun deleteAccount(account: Account) : AccountDaoResult

}

sealed class AccountDaoResult {
    data class OnSuccessFlow(val accounts: Flow<List<Account>>): AccountDaoResult()
    data class OnSuccess(val account: Account): AccountDaoResult()
    data class OnError(val exception: InvalidAccountException): AccountDaoResult()
}

class InvalidAccountException(message: String): Exception(message)