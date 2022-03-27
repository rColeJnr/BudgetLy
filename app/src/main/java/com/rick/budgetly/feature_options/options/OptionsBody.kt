package com.rick.budgetly.feature_options.options

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

//1137
@Composable
fun OptionsBody(viewModel: OptionsViewModel = hiltViewModel(), navController: NavHostController) {

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier) {
            // am going to bed, love me, you.
        }
    }
}