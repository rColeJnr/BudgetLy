package com.rick.budgetly.feature_account.ui.accounts.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.rick.budgetly.ui.theme.toolBar

@Composable
fun AccountTopBar(
    modifier: Modifier,
    title: String,
    secondTitle: String,
    iconRow: @Composable () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        title = {
            Column{
                Text(text = title, style = toolBar.body1, textAlign = TextAlign.Start)
                Text(text = secondTitle, style = toolBar.body2, fontWeight = FontWeight.Light, textAlign = TextAlign.Start)
            }
        },
        actions ={
            iconRow()
        }
    )
}