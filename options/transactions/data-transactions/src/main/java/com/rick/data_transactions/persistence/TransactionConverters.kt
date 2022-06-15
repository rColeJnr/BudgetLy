package com.rick.data_transactions.persistence

import androidx.room.TypeConverter
import java.util.*

class TransactionConverters {
    @TypeConverter fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis
    @TypeConverter fun datastampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }
}