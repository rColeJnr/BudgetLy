package com.rick.budgetly.feature_account.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.Exception

@Entity
data class Account(
    val name: String,
    val type: String,
    val currency: String,
    val balance: String,
    val limit: String,
    val description: String ,
    val image: String,
    var default: Boolean = false,
    var include: Boolean = true,
    @PrimaryKey val id: Int? = null,
)

class InvalidAccountException(message: String): Exception(message)