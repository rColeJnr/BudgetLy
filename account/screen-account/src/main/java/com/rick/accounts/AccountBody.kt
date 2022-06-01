package com.rick.accounts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rick.budgetly_components.AccountTopBar
import com.rick.budgetly_components.BaseRow
import com.rick.common.AccountsScreen
import com.rick.core.formatAmount
import com.rick.data.Account
import com.rick.data.AccountIcon
import com.rick.screen_account.R

@Composable
fun AccountBody(
    accountsViewModel: AccountsViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val context = LocalContext.current
    // Accounts list
    val accounts = accountsViewModel.accountsState.value.accounts

    Surface(
        Modifier
            .fillMaxSize()
            .semantics { contentDescription = context.getString(R.string.accounts_screen) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val amount =
                formatAmount(accounts.map { account -> if (account.include) account.balance.toFloat() else 0f }
                    .sum())
            AccountTopBar(
                modifier = Modifier
                    .wrapContentHeight(),
                title = amount,
                secondTitle = stringResource(R.string.balance),
            ) {
                Icon(
                    imageVector = Icons.Default.PieChart,
                    contentDescription = stringResource(id = R.string.pie_icon),
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .size(26.dp)
                )
            }

            Text(
                text = accountsViewModel.accountsState.value.quote,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(8.dp)
                    .wrapContentHeight()
            )

            AccountList(
                accounts = accounts,
                onAccountClick = {
                    navController.navigate(
                        AccountsScreen.AccountsDetails.name + context.getString(
                            R.string.account_equals, it
                        )
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(align = Alignment.Bottom)
                    .heightIn(0.dp, max = 298.dp)
            )
            // you implement this click here, i guess
            AddNewAccount(modifier = Modifier) { navController.navigate(route = AccountsScreen.AccountsAddEdit.name + "?accountToEdit=${-1}") }
        }
    }
}

@Composable
private fun AddNewAccount(modifier: Modifier, onNewAccountClick: () -> Unit) {
    TextButton(
        onClick = { onNewAccountClick() },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Bottom)
            .padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.PlusOne,
            contentDescription = stringResource(R.string.add_new_account)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = stringResource(R.string.add_new_card), textAlign = TextAlign.Start)
    }
}

@Composable
private fun AccountList(
    accounts: List<Account>,
    onAccountClick: (Int) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(accounts) {
            BaseRow(
                modifier = Modifier
                    .clickable {
                        onAccountClick(it.id!!)
                    }
                    .semantics { contentDescription = "AccountRow" },
                icon = AccountIcon.values()[it.icon].imageVector,
                title = it.title,
                bottomRowText = it.currency,
                balance = it.balance.toFloat()
            )
        }
    }
}

@Preview
@Composable
fun PreviewEverything() {

    Column {
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
        AccountList(accounts = listOf(), onAccountClick = {}, modifier = Modifier.weight(1f))
//        AddNewAccount {}
    }
}