package com.rick.budgetly_components

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult

suspend fun SimpleSnackbar(
    scaffoldSate: ScaffoldState,
    message: String,
    actionLabel: String,
    onDismiss: () -> Unit
) {
    val result = scaffoldSate.snackbarHostState.showSnackbar(
        message = message,
        actionLabel = actionLabel,
        duration = SnackbarDuration.Long
    )
    if (result == SnackbarResult.ActionPerformed) {
        onDismiss()
    }
}
