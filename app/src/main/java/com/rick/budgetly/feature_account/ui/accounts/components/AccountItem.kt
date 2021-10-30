package com.rick.budgetly.feature_account.ui.accounts.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Money
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AccountItem(
    modifier: Modifier,
    title: String,
    balance: String,
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .height(42.dp)
        .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = contentDescription)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title)
        Text(text = balance, modifier = modifier.fillMaxWidth(), style = MaterialTheme.typography.body1, textAlign = TextAlign.End)
    }
}

@Preview
@Composable
fun PreviewAccountItem() {
    AccountItem(
        modifier = Modifier,
        title = "Joaquim",
        balance = "$2000",
        icon = Icons.Filled.Money,
        contentDescription = null,
        onClick = {
            Log.d("log", "it was cliked")
        }
    )
}