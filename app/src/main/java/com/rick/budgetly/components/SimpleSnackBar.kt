package com.rick.budgetly.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SimpleSnackbar(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    show: Boolean = false,
    message: String,
    dismiss: () -> Unit
) {

    if (show){
        LaunchedEffect(scaffoldState.snackbarHostState) {
            val result = scaffoldState.snackbarHostState.showSnackbar(
                message = message,
                actionLabel = "Undo",
                duration = SnackbarDuration.Long
            )
            if (result == SnackbarResult.ActionPerformed) {
                dismiss()
            }
        }
    }

}