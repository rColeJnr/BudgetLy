package com.rick.budgetly.feature_account.ui.accountneworedit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rick.budgetly.R

@Composable
fun AccountAddDetails(
    description: String,
    limit: String,
    balance: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    type: String = stringResource(id = R.string.Type),
    currency: String = stringResource(id = R.string.MZN),
) {

    Surface(Modifier.background(Color.LightGray)) {
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween) {
            AccountTitleDetailColumn(
                title = stringResource(id = R.string.Type),
                detail = type
            )
            Divider()
            AccountTitleDetailColumn(
                title = stringResource(id = R.string.Currency),
                detail = currency
            )
            Divider()
            AccountTitleDetailColumn(
                title = stringResource(id = R.string.Description),
                detail = description
            )

            Spacer(modifier = Modifier.height(16.dp))

            AccountTitleBalanceRow(title = "Credit Limit", balance = limit)
            Divider()
            AccountTitleBalanceRow(title = "Current Balance", balance = balance)

            Spacer(modifier = Modifier.height(16.dp))

            AccountTitleOptionRow(title = "IncludeInTotal", boolean =checked , onCheckedChange = onCheckedChange)
            Divider()
        }
    }
}

@Composable
fun AccountTitleDetailColumn(title: String, detail: String) {
    Column(Modifier.fillMaxWidth()) {
        Text(text = title, style = MaterialTheme.typography.h5)
        Text(text = detail, style = MaterialTheme.typography.body1)
    }
}

@Composable
fun AccountTitleBalanceRow(title: String, balance: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = title, style = MaterialTheme.typography.h5)
        Text(text = balance, style = MaterialTheme.typography.body1)
    }
}

@Composable
fun AccountTitleOptionRow(title: String, boolean: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = title, style = MaterialTheme.typography.h5)
        Switch(checked = boolean, onCheckedChange = onCheckedChange)
    }
}