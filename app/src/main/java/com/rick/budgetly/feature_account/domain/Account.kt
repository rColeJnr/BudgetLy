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
    val include: Boolean = true,
    val main: Boolean = false,
    @PrimaryKey val id: Int? = null
){
    companion object {

    }
}

class InvalidAccountException(message: String): Exception(message)