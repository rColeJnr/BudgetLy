package com.rick.budgetly.feature_account.ui.accounts

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rick.budgetly.R
import com.rick.budgetly.components.BaseRow
import com.rick.budgetly.components.formatAmount
import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.AccountColor
import com.rick.budgetly.feature_account.domain.AccountIcon
import com.rick.budgetly.feature_account.ui.accounts.components.AccountTopBar

@ExperimentalAnimationApi
@Composable
fun AccountScreen(
    accounts: List<Account>,
    onAccountClick: (Account) -> Unit,
    onNewAccountClick: () -> Unit
) {

    val scrollState = rememberScrollState()
    Surface(
        Modifier.fillMaxSize()
    ) {
        //  A column would have done this job just fine, but i wnted to mess with this layout
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val amount =
                formatAmount(accounts.map { account -> account.balance.toFloat() }.sum())
            val dollarSign = if (amount.toFloat() < 0) "-$ " else "$ "
            AccountTopBar(
                modifier = Modifier
                    .wrapContentHeight(),
                title = amount,
                secondTitle = "Balance",
            ) {
                Icon(
                    imageVector = Icons.Default.PieChart,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }

            AccountList(
                accounts = accounts,
                onAccountClick = onAccountClick,
                modifier = Modifier
            )
            // you implement this click here, i guess
            AddNewAccount(onNewAccountClick)
        }
    }
}

@Composable
private fun AddNewAccount(onNewAccountClick: () -> Unit) {
    TextButton(
        onClick = { onNewAccountClick() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom= 8.dp)
    ) {
        Icon(imageVector = Icons.Default.PlusOne, contentDescription = "Add a new account")
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Add a new Card, Debt, Saving, Loan...", textAlign = TextAlign.Start)
    }
}

@Composable
private fun AccountList(accounts: List<Account>, onAccountClick: (Account) -> Unit, modifier: Modifier) {
    LazyColumn(
        modifier.padding(12.dp)
            .wrapContentHeight(Alignment.Bottom),
    ) {
        items(accounts) {
            BaseRow(
                modifier = Modifier.clickable {
                    onAccountClick(it)
                },
                icon = AccountIcon.values()[it.icon].imageVector,
                title = it.title,
                currency = it.currency,
                balance = it.balance.toFloat(),
                negative = it.balance.toFloat() < 0
            )
        }
    }

    Divider(
        Modifier
            .height(16.dp)
    )
}
val list = listOf<Account>(
    Account("title", "type", "Currency", "123123", "limit", "description", 1, AccountColor.Default.color.toArgb(), true, true),
    Account("title", "type", "Currency", "34234", "limit", "description", 2, AccountColor.Default.color.toArgb(), true, true),
    Account("title", "type", "Currency", "4245", "limit", "description", 0, AccountColor.Default.color.toArgb(), true, true),
    Account("title", "type", "Currency", "4245", "limit", "description", 0, AccountColor.Default.color.toArgb(), true, true),
    Account("title", "type", "Currency", "4245", "limit", "description", 0, AccountColor.Default.color.toArgb(), true, true),
    Account("title", "type", "Currency", "4245", "limit", "description", 0, AccountColor.Default.color.toArgb(), true, true),
    Account("title", "type", "Currency", "4245", "limit", "description", 0, AccountColor.Default.color.toArgb(), true, true),
    Account("title", "type", "Currency", "4245", "limit", "description", 0, AccountColor.Default.color.toArgb(), true, true),
    Account("title", "type", "Currency", "63535", "limit", "description", 3, AccountColor.Default.color.toArgb(), true, true)
)

@Preview
@Composable
fun PreviewEverything() {

    Column(
//        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AccountTopBar(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(align = Alignment.Top),
            title = "34785",
            secondTitle = "Balance",
        ) {
            Icon(
                imageVector = Icons.Default.PieChart,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        AccountList(accounts = list, onAccountClick = {} , modifier = Modifier.weight(1f))
        AddNewAccount {

        }
    }
}