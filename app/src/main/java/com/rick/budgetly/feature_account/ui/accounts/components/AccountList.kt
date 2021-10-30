package com.rick.budgetly.feature_account.ui.accounts.components

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rick.budgetly.BudgetLyActivity
import com.rick.budgetly.feature_account.common.ProductionDispatcherProvider
import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.use_case.AccountUseCases
import com.rick.budgetly.feature_account.ui.accounts.*
import javax.inject.Inject

@ExperimentalAnimationApi
@Composable
fun AccountList(
//    navController: NavController,
    viewModel: AccountsViewModel = hiltViewModel()
) {
    // I need a ref of both viewModel and accountLogic in here
    // I dont know how we'll do this, but the last option is to place logic back in viewModel
    val accountLogic = ProvideAccountLogic().accountLogic
    val state = viewModel.accountsState.value
    val scope = rememberCoroutineScope()

    Surface(
        Modifier.fillMaxSize()
    ) {

        val scrollState = rememberScrollState()

        Column(
            Modifier
                .verticalScroll(scrollState)
                .padding(32.dp)
        ) {
            AccountListTitle(title = "CREDIT AND DEBIT CARDS", totalBalance = "$2000") {
                accountLogic.onEvent(AccountEvents.ToggleAccountType(AccountType.CASH))
            }
            AnimatedVisibility(
                visible = state.isAccountTypeVisible[AccountType.CASH.type]!!,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                AccountByTypeList({}, state.accounts )
            }

            Divider(
                Modifier
                    .height(2.dp)
                    .padding(16.dp))

            AccountListTitle(title = AccountType.LOANS.type, totalBalance = "$2000") {
                accountLogic.onEvent(AccountEvents.ToggleAccountType(AccountType.LOANS))
            }
            AnimatedVisibility(
                visible = state.isAccountTypeVisible[AccountType.LOANS.type]!!,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                AccountByTypeList({}, state.accountsByTypeLoans )
            }

            Divider(
                Modifier
                    .height(2.dp)
                    .padding(16.dp))

            AccountListTitle(title = AccountType.SAVINGS.type, totalBalance = "$2000") {
                accountLogic.onEvent(AccountEvents.ToggleAccountType(AccountType.SAVINGS))
            }
            AnimatedVisibility(
                visible = state.isAccountTypeVisible[AccountType.SAVINGS.type]!!,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                AccountByTypeList({}, state.accountsByTypeSavings)
            }

            Divider(
                Modifier
                    .height(2.dp)
                    .padding(16.dp))

            Row(Modifier.fillMaxWidth()) {
                TextButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.PlusOne, contentDescription = "Add an account")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Add a card, deposit, loan ...")
                }
            }
        }
    }

}

@Composable
fun AccountListTitle(
    modifier: Modifier = Modifier,
    title: String,
    totalBalance: String,
    onClick: () -> Unit
) {

    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { onClick() }){
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = totalBalance,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.End,
            modifier = modifier.fillMaxWidth(1f)
        )
    }

}

@Composable
fun AccountItem(
    modifier: Modifier,
    title: String,
    balance: String,
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .height(42.dp)
        .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = contentDescription)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title)
        Text(text = balance, modifier = modifier.fillMaxWidth(), style = MaterialTheme.typography.body1, textAlign = TextAlign.End)
    }
}

@Composable
fun AccountByTypeList(
    onClick: () ->  Unit,
    items: List<Account>
) {

    LazyColumn(){
        items(items) { account ->
            AccountItem(
                modifier = Modifier,
                title = account.name,
                balance = account.balance,
                icon = Icons.Default.AccountBalance,
                contentDescription = "Account balance",
                onClick = onClick
            )
        }
    }

}

@Preview
@Composable
fun PreviewAccountListTitle() {
    AccountListTitle(title = "Title1", totalBalance = "$2000") {

    }
}

@Preview
@Composable
fun PreviewAccountItem() {
    AccountItem(
        modifier = Modifier,
        title = "Joaquim",
        balance = "$2000",
        icon = Icons.Filled.Money,
        contentDescription = null,
        onClick = {
            Log.d("log", "it was cliked")
        }
    )
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewAccountList() {
    val list = List<Account>(9){
        Account("name$it", "type$it", "Currency", "${75 * it}","", "","")
    }
    AccountByTypeList(onClick = { /*TODO*/ }, items = list)
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewAccount() {
    AccountList()
}