package com.rick.budgetly.feature_options.options

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

//1137
@Composable
fun OptionsBody(viewModel: OptionsViewModel = hiltViewModel(), navController: NavHostController) {

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.heightIn(100.dp, 350.dp), verticalArrangement = Arrangement.SpaceBetween) {
            // am going to bed, love me, you.
            OptionsRow(optionText = "Options", optionImage = Icons.Default.Settings, contentDescp = "options"){

            }
            OptionsRow(optionText = "Categories", optionImage = Icons.Default.Category, contentDescp = "categories"){

            }
            OptionsRow(optionText = "Overview", optionImage = Icons.Default.Analytics, contentDescp = "overview"){

            }
            OptionsRow(optionText = "Transactions", optionImage = Icons.Default.History, contentDescp = "transactions"){

            }
        }
    }
}

@Composable
fun OptionsRow(
    optionText: String,
    optionImage: ImageVector,
    contentDescp: String,
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
                .padding(4.dp)
                .clickable { onClick() },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = optionText, style = MaterialTheme.typography.body1, fontFamily = FontFamily.Cursive)
            Image(imageVector = optionImage, contentDescription = contentDescp)
        }
    }
}