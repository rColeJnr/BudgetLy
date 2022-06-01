package com.rick.accounts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rick.add_edit.AccountAddEditBody
import com.rick.common.AccountsScreen
import com.rick.details.AccountDetailsBody

@Composable
fun AccountsNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AccountsScreen.Accounts.name,
        modifier = Modifier
    ) {
        composable(AccountsScreen.Accounts.name) {
            AccountBody(navController = navController)
        }
        val routeAddEdit = AccountsScreen.AccountsAddEdit.name
        composable(
            route = "$routeAddEdit?accountToEdit={accountToEdit}",
            arguments = listOf(
                navArgument("accountToEdit") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AccountAddEditBody(
                modifier = Modifier,
                navController = navController
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
        ) {
            AccountDetailsBody(
                onNavigationIconClick = { navController.navigateUp() },
                onSettingsClick = {
                    navController.navigate(route = AccountsScreen.AccountsAddEdit.name + "?accountToEdit=${it}")
                },
                // i am undecided about moving the all the state up here or passing navController and viewModel as parameters
                // so it's salad.
                navController = navController
            )
        }
    }
}
