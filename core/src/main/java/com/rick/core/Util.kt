package com.rick.core

import java.text.DecimalFormat


private fun dollarSign(amount: Float): String{
    return if (amount < 0) "-$ " else "$ "
}

private val AmountDecimalFormat = DecimalFormat("#,###.##")

fun formatAmount(amount: Float): String {
    return AmountDecimalFormat.format(amount)
}