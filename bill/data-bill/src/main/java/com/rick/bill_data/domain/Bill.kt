package com.rick.bill_data.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Bill(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val amount: Float,
    val dueDate: Calendar,
    val icon: Int,
    val isPaid: Boolean,
    val isArchived: Boolean
)

enum class BillIcon(val imageVector: ImageVector, val contentDescription: String){
    INTERNET(Icons.Default.NetworkWifi, Icons.Default.NetworkWifi.name),
    TV(Icons.Default.Tv, Icons.Default.Tv.name),
    RENT(Icons.Default.House, Icons.Default.House.name),
    PHONE(Icons.Default.NetworkCell, Icons.Default.Phone.name),
    LOAN(Icons.Default.MoneyOff, Icons.Default.MoneyOff.name),
    LIGHT(Icons.Default.Lightbulb, Icons.Default.Lightbulb.name),
    GAS(Icons.Default.LocalGasStation, Icons.Default.LocalGasStation.name),
    BUS(Icons.Default.CarRental, Icons.Default.CarRental.name),
    BOOKS(Icons.Default.LibraryBooks, Icons.Default.LibraryBooks.name);

    companion object {
        val Default = INTERNET
        const val Position = 4
    }

}

class InvalidBillException(message: String): Exception(message)
