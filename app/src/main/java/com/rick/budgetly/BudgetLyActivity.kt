package com.rick.budgetly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rick.budgetly.components.BudgetLyTabRow
import com.rick.budgetly.feature_account.ui.accountdetails.AccountDetailsViewModel
import com.rick.budgetly.feature_account.ui.accountneworedit.AccountAddEditViewModel
import com.rick.budgetly.feature_account.ui.accounts.AccountBody
import com.rick.budgetly.feature_account.ui.accounts.AccountsViewModel
import com.rick.budgetly.feature_account.ui.util.dummyAccounts
import com.rick.budgetly.ui.theme.BudgetLyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BudgetLyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BudgetLyApp(
                accountsViewModel = hiltViewModel(),
                addEditViewModel = hiltViewModel(),
                detailsViewModel = hiltViewModel(),
            )
        }
    }
}

@Composable
fun BudgetLyApp(
    accountsViewModel: AccountsViewModel,
    addEditViewModel: AccountAddEditViewModel,
    detailsViewModel: AccountDetailsViewModel
) {
    BudgetLyTheme {
        val allScreens = BudgetLyScreen.values().toList()
        val navController = rememberNavController()
        val backStackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = BudgetLyScreen.fromRoute(backStackEntry.value?.destination?.route)

        Scaffold(
            bottomBar = {
                BottomNavigation {
                    BudgetLyTabRow(
                        allScreens = allScreens,
                        onTabSelected = { screen -> navController.navigate(screen.name) },
                        currentScreen = currentScreen
                    )
                }
            }
        ) { innerPadding ->
            BudgetLyNavHost(
                accountsViewModel = accountsViewModel,
                accountsAddEditViewModel = addEditViewModel,
                accountsDetailsViewModel = detailsViewModel,
                navController,
                Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun BudgetLyNavHost(
    accountsViewModel: AccountsViewModel,
    accountsAddEditViewModel: AccountAddEditViewModel,
    accountsDetailsViewModel: AccountDetailsViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BudgetLyScreen.Accounts.name,
        modifier = modifier
    ) {
        composable(BudgetLyScreen.Accounts.name) {
            accountsViewModel.onStart()
            AccountBody(
                accountsViewModel = accountsViewModel,
                accountsAddEditViewModel = accountsAddEditViewModel,
                accountsDetailsViewModel = accountsDetailsViewModel,
            )
        }
        composable(BudgetLyScreen.Transactions.name){
            Text(text = "Stupid text2")
        }
        composable(BudgetLyScreen.Overview.name){
            Text(text = "Stupid text3")
        }
    }
}

