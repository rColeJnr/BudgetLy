package com.rick.budgetly.feature_account.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

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