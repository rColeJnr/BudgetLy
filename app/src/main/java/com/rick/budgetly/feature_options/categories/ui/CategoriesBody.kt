package com.rick.budgetly.feature_options.categories.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rick.budgetly.feature_options.categories.domain.Category
import com.rick.budgetly.feature_options.categories.domain.CategoryImages

@Composable
fun CategoriesBody(navController: NavHostController) {
    Text(text = "This is Categories screen")
}

@Composable
fun CategoriesRow(category: Category) {
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
            Text(text = category.name)
            // TODO get icons from int position
            Icon(imageVector = Icons.Default.FoodBank, contentDescription = CategoryImages.values()[category.icon].name)
        }
    }

}