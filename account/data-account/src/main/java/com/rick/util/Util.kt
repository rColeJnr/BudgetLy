package com.rick.util

import com.rick.data.Account
import java.text.DecimalFormat

fun getMainAccount(accounts: List<Account>): Account {
    return accounts.first { it.main }
}

fun getAccount(accounts: List<Account>, accountId: Int?): Account? {
    return if (accountId == -1) null else accounts.first { it.id == accountId }
}