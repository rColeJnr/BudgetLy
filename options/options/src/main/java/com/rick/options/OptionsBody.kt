package com.rick.options

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rick.budgetly_components.DefaultRow

//1137
@Composable
fun OptionsBody(navController: NavHostController) {

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // am going to bed, love me, you.
            AnimatedPieChart()
            UserDetailsBox(name = "user email", isActive = true, profilePic = Icons.Default.Person)

            DefaultRow(
                text = stringResource(R.string.options),
                image = Icons.Default.Settings,
                description = stringResource(R.string.options)
            ) {
                navController.navigate(OptionsScreen.Settings.name)
            }
            DefaultRow(
                text = stringResource(R.string.categories),
                image = Icons.Default.Category,
                description = stringResource(R.string.categories)
            ) {
                navController.navigate(OptionsScreen.Categories.name)
            }
            DefaultRow(
                text = stringResource(R.string.overview),
                image = Icons.Default.Analytics,
                description = stringResource(R.string.overview)
            ) {
                navController.navigate(OptionsScreen.Overview.name)
            }
            DefaultRow(
                text = stringResource(id = R.string.transactions),
                image = Icons.Default.History,
                description = stringResource(R.string.transactions)
            ) {
                navController.navigate(OptionsScreen.Transactions.name)
            }
        }
    }
}

@Composable
fun AnimatedPieChart() {
}

@Composable
fun UserDetailsBox(
    name: String,
    isActive: Boolean,
    profilePic: ImageVector
) {
    Box(modifier = Modifier
        .padding(top = 0.dp, bottom = 16.dp, start = 8.dp, end = 8.dp)
        .height(80.dp)
        .border(2.dp, color = Color.DarkGray, shape = RoundedCornerShape(16.dp))
    ){
        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.wrapContentHeight(), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.Start) {
                Text(text = name, style = MaterialTheme.typography.body1, fontFamily = FontFamily.SansSerif)
                Text(text = "Sync: $isActive", style = MaterialTheme.typography.body1, fontFamily = FontFamily.SansSerif)
            }
            Image(imageVector = profilePic, contentDescription = null, colorFilter = ColorFilter.tint(Color.White))
        }
    }
}

@Preview
@Composable
fun PrevOptionsRow() {
    DefaultRow(
        text = "Option Text",
        image = Icons.Outlined.Star,
        description = "star"
    ) {

    }
}

@Preview
@Composable
fun PrevUserDetails() {
    UserDetailsBox(name = "User 1", isActive = true, profilePic = Icons.Rounded.Person)
}