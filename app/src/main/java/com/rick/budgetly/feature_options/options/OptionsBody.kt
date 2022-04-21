package com.rick.budgetly.feature_options.options

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

//1137
@Composable
fun OptionsBody(viewModel: OptionsViewModel = hiltViewModel(), navController: NavHostController) {

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // am going to bed, love me, you.
            AnimatedPieChart()

            UserDetailsBox(name = "aldfjd", isActive = true, profilePic = Icons.Default.Person)

            OptionsRow(optionText = "Options", optionImage = Icons.Default.Settings, contentDescp = "options"){
                navController.navigate(OptionsScreen.Settings.name)
            }
            OptionsRow(optionText = "Categories", optionImage = Icons.Default.Category, contentDescp = "categories"){
                navController.navigate(OptionsScreen.Categories.name)
            }
            OptionsRow(optionText = "Overview", optionImage = Icons.Default.Analytics, contentDescp = "overview"){
                navController.navigate(OptionsScreen.Overview.name)
            }
            OptionsRow(optionText = "Transactions", optionImage = Icons.Default.History, contentDescp = "transactions"){
                navController.navigate(OptionsScreen.Transactions.name)
            }
        }
    }
}

@Composable
fun AnimatedPieChart() {
    Box(
        Modifier
            .padding(vertical = 24.dp)
            .size(255.dp)
            .background(color = Color.White, shape = RoundedCornerShape(360.dp)))
}

@Composable
fun UserDetailsBox(
    name: String,
    isActive: Boolean,
    profilePic: ImageVector
) {
    Box(modifier = Modifier
        .padding(vertical = 32.dp, horizontal = 8.dp)
        .height(80.dp)
        .border(2.dp, color = Color.DarkGray, shape = RoundedCornerShape(16.dp))
    ){
        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(12.dp),
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

@Composable
fun OptionsRow(
    optionText: String,
    optionImage: ImageVector,
    contentDescp: String ="",
    onClick: () -> Unit
) {
    Box(modifier = Modifier
        .padding(8.dp)
        .border(
            width = 2.dp,
            color = Color.DarkGray,
            shape = RoundedCornerShape(16.dp)
        )
    ){
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable { onClick() }
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = optionText, style = MaterialTheme.typography.body1, fontFamily = FontFamily.SansSerif)
            Image(imageVector = optionImage, contentDescription = contentDescp, colorFilter = ColorFilter.tint(Color.White))
        }
    }
}