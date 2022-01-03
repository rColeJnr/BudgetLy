package com.rick.budgetly.feature_bills.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rick.budgetly.feature_bills.domain.Bill

@Database(entities = [Bill::class], version = 1, exportSchema = false)
@TypeConverters(BillConverters::class)
abstract class BillDatabase: RoomDatabase() {

    abstract val billDao: BillDao

}