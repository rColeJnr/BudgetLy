package com.rick.accounts

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rick.accounts.BackPressHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BaseBottomSheet(
    state: ModalBottomSheetState,
    scope: CoroutineScope,
    navController: NavHostController,
    sheetContent: @Composable () -> Unit,
    screenContent: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        sheetState = state,
        sheetElevation = 16.dp,
        sheetContent = {
            sheetContent()
            BackPressHandler {
                if (state.isVisible){ scope.launch { state.hide() } }
                else {navController.navigateUp()}
            }
        },
    ) {
        screenContent()
    }
}