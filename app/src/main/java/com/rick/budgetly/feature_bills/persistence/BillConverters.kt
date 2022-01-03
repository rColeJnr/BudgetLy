package com.rick.budgetly.feature_bills.persistence

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.TypeConverter
import java.util.*

class BillConverters {
    @TypeConverter fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis
    @TypeConverter fun datastampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }
}