package com.rick.budgetly.feature_account.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    val title: String,
    val type: String,
    val currency: String,
    var balance: String,
    val limit: String,
    val description: String,
    val icon: Int,
    val color: Int,
    val include: Boolean = true,
    var main: Boolean = false,
    @PrimaryKey val id: Int? = null
)

enum class AccountType(val type: String) {
    CASH("CASH"),
    DEBTS("DEBTS"),
    LOANS("LOANS"),
    SAVINGS("SAVINGS");

    companion object {
        val Default = CASH
    }
}

enum class AccountIcon(val imageVector: ImageVector, val contentDescription: String) {
    Money(Icons.Default.Money, Icons.Default.Money.name),
    Bank(Icons.Default.CommentBank, Icons.Default.CommentBank.name),
    Safe(Icons.Default.Savings, Icons.Default.Savings.name),
    Wallet(Icons.Default.AccountBalanceWallet, Icons.Default.AccountBalanceWallet.name),
    Card(Icons.Default.CreditCard, Icons.Default.CreditCard.name),
    Coins(Icons.Default.PriceChange,Icons.Default.PriceChange.name),
    Face(Icons.Default.Face, Icons.Default.Face.name);

    companion object {
        val Default = Card
        const val Position = 4
    }

}

enum class AccountColor(val color: Color) {
    Red(Color.Red),
    Magenta(Color.Magenta),
    Blue(Color.Blue),
    Gray(Color.Gray),
    Cyan(Color.Black),
    DarkGray(Color.DarkGray),
    Green(Color.Green);

    companion object {
        val Default = Green
        const val Position = 6
    }
}

enum class AccountCurrency(val currency: String){
    MZN("Mozambican Metical"),
    RAND("South African Rand"),
    KWANZA("Angolan Kwanza"),
    USD("United States Dollar"),
    RUB("Russian Ruble"),
    EURO("Euro");

    companion object {
        val Default = MZN
    }
}

class InvalidAccountException(message: String) : Exception(message)
