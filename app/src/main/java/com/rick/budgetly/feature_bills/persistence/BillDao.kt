package com.rick.budgetly.feature_bills.persistence

import androidx.room.*
import com.rick.budgetly.feature_bills.domain.Bill
import kotlinx.coroutines.flow.Flow

@Dao
interface BillDao {

    @Query("SELECT * FROM bill ORDER BY dueDate")
    fun getBills(): Flow<List<Bill>>

    @Query("SELECT * FROM bill WHERE id = :id")
    fun getBillById(id: Int): Flow<Bill?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bill: Bill)

    @Update
    suspend fun update(bill: Bill)

    @Delete
    suspend fun delete(bill: Bill)
}