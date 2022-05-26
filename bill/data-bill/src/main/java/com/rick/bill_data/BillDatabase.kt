package com.rick.bill_data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rick.bill_data.domain.Bill

@Database(entities = [Bill::class], version = 1, exportSchema = false)
@TypeConverters(BillConverters::class)
abstract class BillDatabase: RoomDatabase() {

    abstract val billDao: BillDao

}