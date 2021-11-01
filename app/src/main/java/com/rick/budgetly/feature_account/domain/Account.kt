package com.rick.budgetly.feature_account.domain

import androidx.annotation.StringRes
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rick.budgetly.R
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
)

enum class AccountIcon(val imageVector: ImageVector, @StringRes val contentDescription: Int){
    Money(Icons.Default.Money , R.string.Money ),
    Bank(Icons.Default.CommentBank , R.string.Bank),
    Safe(Icons.Default.Savings , R.string.Safe),
    Wallet(Icons.Default.AccountBalanceWallet , R.string.Wallet),
    Card(Icons.Default.CreditCard , R.string.Card),
    Coins(Icons.Default.PriceChange , R.string.Coins),
    Smile(Icons.Default.Face , R.string.Smile);

    companion object {
        val Default = Card
    }

}

enum class AccountColor(val color: Color){
    Red(Color.Red),
    Magenta(Color.Magenta),
    Blue(Color.Blue),
    Gray(Color.Gray),
    cyan(Color.Black),
    DarkGray(Color.DarkGray),
    Green(Color.Green);

    companion object {
        val Default = Green
    }
}

class InvalidAccountException(message: String): Exception(message)