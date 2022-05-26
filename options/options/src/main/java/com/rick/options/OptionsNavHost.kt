package com.rick.options

import OptionsBody
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rick.budgetly.BudgetLyScreen
import com.rick.screen_categories.ui.CategoriesBody
import com.rick.budgetly.feature_options.overview.OverviewBody
import com.rick.budgetly.feature_options.settings.ui.SettingsBody
import com.rick.budgetly.feature_options.transactions.ui.TransactionsBody

@Composable
fun OptionsNavHost() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = BudgetLyScreen.Options.name){
        composable(OptionsScreen.Options.name){
            OptionsBody(navController = navController)
        }
        composable(OptionsScreen.Categories.name){
            CategoriesBody(navController = navController)
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