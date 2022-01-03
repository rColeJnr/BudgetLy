package com.rick.budgetly.feature_options.transactions.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Transaction(
    val type: String,
    val date: Calendar,
    val sender: String,
    val receiver: String,
    val amount: Int,
    @PrimaryKey @ColumnInfo(name = "id") val id: Int
)

enum class TransactionType(val type: String){
    Transfer("Transfer"),
    Payment("Payment"),
    Deposit("Deposit"),
    Withdraw("Withdraw")
}
