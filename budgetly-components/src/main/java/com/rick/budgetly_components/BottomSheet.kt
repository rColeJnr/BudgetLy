package com.rick.budgetly_components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BaseBottomSheet(
    state: ModalBottomSheetState,
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