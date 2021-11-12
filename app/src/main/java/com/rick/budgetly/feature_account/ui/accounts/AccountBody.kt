package com.rick.budgetly.feature_account.ui.accounts

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rick.budgetly.components.BaseRow
import com.rick.budgetly.feature_account.common.AccountsScreen
import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.AccountIcon
import com.rick.budgetly.feature_account.ui.accountdetails.AccountDetailsBody
import com.rick.budgetly.feature_account.ui.accountdetails.AccountDetailsEvents
import com.rick.budgetly.feature_account.ui.accountdetails.AccountDetailsViewModel
import com.rick.budgetly.feature_account.ui.accountneworedit.AccountAddEditBody
import com.rick.budgetly.feature_account.ui.accountneworedit.AccountAddEditViewModel
import com.rick.budgetly.feature_account.ui.components.AccountTopBar
import com.rick.budgetly.feature_account.ui.util.dummyAccounts
import com.rick.budgetly.feature_account.ui.util.formatAmount
import com.rick.budgetly.feature_account.ui.util.getAccount

@Composable
fun AccountBody(
    accounts: List<Account>,
    accountsAddEditViewModel: AccountAddEditViewModel,
    accountsDetailsViewModel: AccountDetailsViewModel
) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AccountsScreen.Accounts.name,
        modifier = Modifier
    ){
        composable(AccountsScreen.Accounts.name){
            Surface(
                Modifier.fillMaxSize()
            ) {
                //  A column would have done this job just fine, but i wnted to mess with this layout
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val amount =
                        formatAmount(accounts.map { account -> account.balance.toFloat() }.sum())
                    AccountTopBar(
                        modifier = Modifier
                            .wrapContentHeight(),
                        title = amount,
                        secondTitle = "Balance",
                    ) {
                        Icon(
                            imageVector = Icons.Default.PieChart,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .size(26.dp)
                        )
                    }

                    Text(text = "Random kanye Qoute", style = MaterialTheme.typography.h4)

                    AccountList(
                        accounts = accounts,
                        onAccountClick = { navController.navigate(AccountsScreen.AccountsDetails.name + "account=${it}") },
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
        val routeAddEdit = AccountsScreen.AccountsAddEdit.name
        composable(
            route = "$routeAddEdit?accountToEdit={accountToEdit}",
            arguments = listOf(
                navArgument(accountToEdit) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { entry ->
            val account = entry.arguments?.getInt(accountToEdit)
            AccountAddEditBody(
                modifier = Modifier,
                account = getAccount(accounts, account),
                navController = navController,
                viewModel = accountsAddEditViewModel
            )
        }

        val accountsName = AccountsScreen.AccountsDetails.name
        composable(
            route = accountsName + "account={account}",
            arguments = listOf(
                navArgument("account") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            val account = entry.arguments?.getInt("account")
            AccountDetailsBody(
                serializableAccount = getAccount(accounts, account)!!,
                viewModel = accountsDetailsViewModel,
                onNavigationIconClick = { navController.navigateUp() },
                onSettingsClick = {
                    navController.navigate(route = AccountsScreen.AccountsAddEdit.name + "?accountToEdit=${it.id}")
                },
                // i am undecided about moving the all the state up here or passing navController and viewModel as parameters
                // so it's salad.
                onDeleteClick = {
                    accountsDetailsViewModel.onEvent(AccountDetailsEvents.DeleteAccount(accountsDetailsViewModel.currentAccount!!))
                    navController.navigateUp()
                }
            )
        }

    }

}

private const val accountToEdit = "accountToEdit"

@Composable
private fun AddNewAccount(modifier: Modifier, onNewAccountClick: () -> Unit) {
    TextButton(
        onClick = { onNewAccountClick() },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Bottom)
            .padding(bottom = 8.dp)
    ) {
        Icon(imageVector = Icons.Default.PlusOne, contentDescription = "Add a new account")
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Add a new Card, Debt, Saving, Loan...", textAlign = TextAlign.Start)
    }
}

@Composable
private fun AccountList(accounts: List<Account>, onAccountClick: (Int) -> Unit, modifier: Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        items(accounts) {
            BaseRow(
                modifier = Modifier.clickable {
                    onAccountClick(it.id!!)
                },
                icon = AccountIcon.values()[it.icon].imageVector,
                title = it.title,
                currency = it.currency,
                balance = it.balance.toFloat()
            )
        }
    }
}
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
        AccountList(accounts = dummyAccounts, onAccountClick = {} , modifier = Modifier.weight(1f))
//        AddNewAccount {}
    }
}