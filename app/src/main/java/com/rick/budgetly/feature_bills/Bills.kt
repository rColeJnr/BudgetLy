package com.rick.budgetly.feature_bills

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun Bills() {
    Text(text = "Fuck worked", modifier = Modifier.semantics { contentDescription = "Bills Screen" })
}

