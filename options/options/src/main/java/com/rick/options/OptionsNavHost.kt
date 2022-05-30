package com.rick.options

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rick.core.BudgetLyScreen
import com.rick.overview.OverviewBody
import com.rick.screen_categories.ui.CategoriesBody
import com.rick.screen_transactions.ui.TransactionsBody
import com.rick.settings.SettingsBody

@Composable
fun OptionsNavHost() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = BudgetLyScreen.Options.name){
        composable(OptionsScreen.Options.name){
            OptionsBody(navController = navController)
        }
        composable(OptionsScreen.Categories.name){
            CategoriesBody(controlNavigation = {})
        }
        composable(OptionsScreen.Transactions.name){
            TransactionsBody(navController = navController)
        }
        composable(OptionsScreen.Overview.name){
            OverviewBody(navController = navController)
        }
        composable(OptionsScreen.Settings.name){
            SettingsBody(navController = navController)
        }
    }
}