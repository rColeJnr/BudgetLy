package com.rick.budgetly.feature_account.ui.util

import com.rick.budgetly.feature_account.domain.Account
import java.text.DecimalFormat

fun dollarSign(amount: Float): String{
    return if (amount < 0) "-$ " else "$ "
}

fun formatAmount(amount: Float): String {
    return dollarSign(amount) + AmountDecimalFormat.format(amount)
}

private val AmountDecimalFormat = DecimalFormat("#,###.##")

fun getMainAccount(accounts: List<Account>): Account {
    return accounts.first { it.main }
}

fun getAccount(accounts: List<Account>, accountId: Int?): Account? {
    return if (accountId == -1) null else accounts.first { it.id == accountId }
}

val dummyAccounts = listOf(
    Account("Thick", "Thick", "Booty", "34345", "93443", "Small Boobs", 1, 3, id = 0),
    Account("Thick", "Thick", "Booty", "34345", "93443", "Small Boobs", 1, 3, id = 1),
    Account("Thick", "Thick", "Booty", "34345", "93443", "Small Boobs", 1, 3, id = 2),
    Account("Thick", "Thick", "Booty", "34345", "93443", "Small Boobs", 1, 3, id = 3),
)