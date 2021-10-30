package com.rick.budgetly.feature_account.ui.accounts.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AccountListTitle(
    modifier: Modifier = Modifier,
    title: String,
    totalBalance: String,
    onClick: () -> Unit
) {

    Row(modifier = modifier.fillMaxWidth().clickable { onClick() }){
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1,
            modifier = modifier.fillMaxWidth(1f)
        )
        Text(
            text = totalBalance,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.End
        )
    }

}