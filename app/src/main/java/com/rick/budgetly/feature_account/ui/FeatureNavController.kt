package com.rick.budgetly.feature_account.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rick.budgetly.components.BaseBottomSheet
import com.rick.budgetly.components.Calculator
import com.rick.budgetly.feature_account.common.AccountsScreen
import com.rick.budgetly.feature_account.ui.accountdetails.AccountDetailsBody
import com.rick.budgetly.feature_account.ui.accountneworedit.AccountAddEditBody
import com.rick.budgetly.feature_account.ui.accounts.AccountBody

@ExperimentalMaterialApi
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
