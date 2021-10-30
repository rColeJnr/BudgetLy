package com.rick.budgetly.feature_account.ui.accounts.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.ui.accounts.AccountEvents
import com.rick.budgetly.feature_account.ui.accounts.AccountLogic
import com.rick.budgetly.feature_account.ui.accounts.AccountType
import com.rick.budgetly.feature_account.ui.accounts.AccountsViewModel

@ExperimentalAnimationApi
@Composable
fun AccountList(
//    navController: NavController,
    viewModel: AccountsViewModel = hiltViewModel()
) {
    val state = viewModel.accountsState.value
    val accountLogic = viewModel.accountLogic
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

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewAccountList() {
    AccountList(viewModel= hiltViewModel())
}