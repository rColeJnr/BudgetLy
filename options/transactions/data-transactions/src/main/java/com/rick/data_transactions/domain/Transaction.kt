package com.rick.data_transactions.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.Exception
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
    TRANSFER("Transfer"),
    PAYMENT("Payment"),
    DEPOSIT("Deposit"),
    WITHDRAW("Withdraw")
}

class TransactionException(message: String): Exception(message)