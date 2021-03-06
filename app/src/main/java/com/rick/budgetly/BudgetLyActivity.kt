package com.rick.budgetly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rick.accounts.AccountsNavHost
import com.rick.bills.BillsBody
import com.rick.core.theme.BudgetLyTheme
import com.rick.options.OptionsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BudgetLyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BudgetLyApp()
        }
    }
}

@Composable
fun BudgetLyApp() {
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
                navController,
                Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun BudgetLyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BudgetLyScreen.Accounts.name,
        modifier = modifier
    ) {
        composable(BudgetLyScreen.Accounts.name) {
            AccountsNavHost()
        }
        composable(BudgetLyScreen.Bills.name){
            BillsBody(navController = navController)
        }
        composable(BudgetLyScreen.Options.name){
            OptionsNavHost()
        }
    }
}

