package com.rick.budgetly.feature_account.ui.util

import com.rick.budgetly.feature_account.domain.Account
import java.text.DecimalFormat

private fun dollarSign(amount: Float): String{
    return if (amount < 0) "-$ " else "$ "
}

private val AmountDecimalFormat = DecimalFormat("#,###.##")

fun formatAmount(amount: Float): String {
    return AmountDecimalFormat.format(amount)
}

fun getMainAccount(accounts: List<Account>): Account {
    return accounts.first { it.main }
}

fun getAccount(accounts: List<Account>, accountId: Int?): Account? {
    return if (accountId == -1) null else accounts.first { it.id == accountId }
}

val dummyAccounts = listOf(
    Account("Thick", "Baddie", "Booty", "34345", "93443", "Small Boobs", 0, 3, id = 0),
    Account("Thick", "Wifey", "Booty", "34345", "93443", "Nice Boobs", 1, 3, id = 1),
    Account("Thick", "Soft hearted", "Booty", "34345", "93443", "Small Boobs", 2, 3, id = 2),
    Account("Thick", "Adventurous", "Booty", "34345", "93443", "Small Boobs", 3, 3, id = 3),
    Account("Thick", "Introverted", "Booty", "34345", "93443", "Medium sized Boobs", 4, 3, id = 4),
    Account("Thick", "Extroverted", "Booty", "34345", "93443", "Small Boobs", 5, 3, id = 5),
)