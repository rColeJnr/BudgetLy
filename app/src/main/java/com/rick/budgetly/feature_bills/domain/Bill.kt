package com.rick.budgetly.feature_bills.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bill(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val amount: Int,
    val dueData: Int,
    val icon: Int,
    val isPaid: Boolean,
    val isArchived: Boolean
)

enum class BillIcon(val imageVector: ImageVector, val contentDescription: String){
    INTERNET(Icons.Default.NetworkWifi, "InternetIcon"),
    TV(Icons.Default.Tv, "TvIcon"),
    RENT(Icons.Default.House, "HouseIcon"),
    PHONE(Icons.Default.NetworkCell, "PhoneIcon"),
    LOAN(Icons.Default.MoneyOff, "LoanIcon");

    companion object {
        val Default = INTERNET
    }

}

class InvalidBillException(message: String): Exception(message)
