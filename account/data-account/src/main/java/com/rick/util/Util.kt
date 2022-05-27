package com.rick.util

import com.rick.data.Account
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