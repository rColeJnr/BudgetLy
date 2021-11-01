package com.rick.budgetly.feature_account.ui.accounts

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.rick.budgetly.feature_account.ui.accounts.components.AccountByTypeList
import com.rick.budgetly.feature_account.ui.accounts.components.AccountListTitle
import com.rick.budgetly.feature_account.ui.accounts.components.AccountTopBar

@ExperimentalAnimationApi
@Composable
fun AccountList(
    navController: NavController?,
    viewModel: AccountsViewModel
) {
    // I need a ref of both viewModel and accountLogic in here
    // I dont know how we'll do this, but the last option is to place logic back in viewModel
    val state = viewModel.accountsState.value
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    Surface(
        Modifier.fillMaxSize()
    ) {
        //  A column would have done this job just fine, but i wnted to mess with this layout
        ConstraintLayout(Modifier.fillMaxSize()) {
            val (topBar, column, button) = createRefs()
            AccountTopBar(
                modifier = Modifier.constrainAs(topBar) {
                    top.linkTo(parent.top)
                },
                title = "title",
                secondTitle = "second title",
            ) {
                Icon(imageVector = Icons.Default.PieChart, contentDescription = null)
                Spacer(modifier = Modifier.width(16.dp))
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }

            Column(
                Modifier
                    .constrainAs(column) {
                        top.linkTo(topBar.bottom)
                    }
                    .verticalScroll(scrollState)
                    .padding(16.dp)
            ) {
                AccountListTitle(title = "CREDIT AND DEBIT CARDS", totalBalance = "$2000") {
                    viewModel.onEvent(AccountEvents.ToggleAccountType(AccountType.CASH))
                }
                AccountByTypeList(state.accounts, state.isAccountTypeVisible[AccountType.LOANS.type]!!){
                    // TODO (Expand the view)
                }

                Divider(
                    Modifier
                        .height(2.dp)
                        .padding(vertical = 16.dp)
                )

                AccountListTitle(title = AccountType.LOANS.type, totalBalance = "$2000") {
                    viewModel.onEvent(AccountEvents.ToggleAccountType(AccountType.LOANS))
                }

                AccountByTypeList(state.accountsByTypeLoans, state.isAccountTypeVisible[AccountType.LOANS.type]!!){

                }

                Divider(
                    Modifier
                        .height(2.dp)
                        .padding(vertical = 16.dp)
                )

                AccountListTitle(title = AccountType.SAVINGS.type, totalBalance = "$2000") {
                    viewModel.onEvent(AccountEvents.ToggleAccountType(AccountType.SAVINGS))
                }
                AccountByTypeList(state.accountsByTypeSavings, state.isAccountTypeVisible[AccountType.SAVINGS.type]!!){

                }
                Divider(
                    Modifier
                        .height(2.dp)
                        .padding(vertical = 16.dp)
                )

                Row(Modifier.fillMaxWidth()) {
                    TextButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.PlusOne,
                            contentDescription = "Add an account"
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Add a card, deposit, loan ...")
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = { /*TODO*/ }, modifier = Modifier
                .constrainAs(button) {
                    top.linkTo(column.bottom)
                }
                .fillMaxWidth()) {
                Icon(imageVector = Icons.Default.PlusOne, contentDescription = "Add a new account")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Add a new Card, Debt, Saving, Loan...", textAlign = TextAlign.Start)
            }
        }
    }
}