package com.rick.budgetly.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BaseBottomSheet(state: ModalBottomSheetState, scope: CoroutineScope) {
    ModalBottomSheetLayout(
        modifier = Modifier.height(300.dp),
        sheetState = state,
        sheetContent = {
            LazyColumn {
                items(3) {
                    ListItem(
                        text = { Text("Item $it") },
                        icon = {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    )
                }
            }
        },
    ) {
        Button(onClick = { scope.launch { state.show() } }) {
            Text("Click to show sheet")
        }
    }
}