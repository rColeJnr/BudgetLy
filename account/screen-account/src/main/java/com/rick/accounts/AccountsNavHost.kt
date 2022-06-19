package com.rick.accounts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rick.common.AccountsScreen

@Composable
fun AccountsNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AccountsScreen.Accounts.name,
        modifier = Modifier
    ) {


    }
}
