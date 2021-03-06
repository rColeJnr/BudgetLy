package com.rick.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun OverviewBody(navController: NavHostController) {

    Surface() {
        Column(Modifier.scrollable(rememberScrollState(), orientation = Orientation.Vertical), horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedPieChart()
            OverviewRow(title = stringResource(R.string.account_name, stringResource(id = R.string.MZN)), amount = 454f, percentage = 7.5f) {

            }
            OverviewRow(title = stringResource(R.string.account_name, stringResource(R.string.RUBLE)), amount = 454f, percentage = 45.4f) {

            }
            OverviewRow(title = stringResource(R.string.total_money), amount = 454f, percentage = 5f) {

            }
            OverviewRow(title = stringResource(R.string.bills), amount = 454f, percentage = 4.5f) {

            }
            OverviewRow(title = stringResource(id = R.string.overview), amount = 454f, percentage = 54.2f) {
                Icon(imageVector = Icons.Default.ArrowRight, contentDescription = stringResource(R.string.show_categories))
            }
        }
    }

}

@Composable
fun AnimatedPieChart() {
    Box(
        Modifier
            .padding(vertical = 20.dp)
            .size(255.dp)
            .background(color = Color.White, shape = RoundedCornerShape(360.dp)))
}


@Composable
fun OverviewRow(title: String, amount: Float, percentage: Float, showCategories: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(
                width = 2.dp,
                color = Color.DarkGray,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Column(modifier = Modifier.wrapContentWidth(Alignment.Start)) {
                Text(text = title)
                Text(text = "$percentage%")
            }
            Column(modifier = Modifier.wrapContentWidth(Alignment.End)) {
                Text(text = "$amount$")
                showCategories()
            }
        }
    }
    
}