package com.rick.budgetly.feature_options.options

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rick.budgetly.BudgetLyScreen

@Composable
fun OptionsNavHost() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = BudgetLyScreen.Options.name){

    }
}