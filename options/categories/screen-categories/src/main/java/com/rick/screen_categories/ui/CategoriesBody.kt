package com.rick.screen_categories.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rick.data_categories.domain.Category
import com.rick.data_categories.domain.CategoryImages
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoriesBody(controlNavigation: () -> Unit) {
    
    val scaffoldState = rememberScaffoldState()

    val context = LocalContext.current

    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    
    Scaffold(Modifier.fillMaxSize(), scaffoldState = scaffoldState) {
        
        BaseBottomSheet(
            state = modalBottomSheetState,
            scope = scope,
            controlNavigation = { controlNavigation() },
            sheetContent = {  }
        ) {
            LazyColumn{
                items(listOf<Category>()){
                    com.rick.screen_categories.ui.CategoriesRow(category = it)
                }
            }
        }
        
    }
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BaseBottomSheet(
    state: ModalBottomSheetState,
    scope: CoroutineScope,
    controlNavigation: @Composable () -> Unit,
    sheetContent: @Composable () -> Unit,
    screenContent: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        sheetState = state,
        sheetElevation = 16.dp,
        sheetContent = {
            sheetContent()
            controlNavigation()
        },
    ) {
        screenContent()
    }
}