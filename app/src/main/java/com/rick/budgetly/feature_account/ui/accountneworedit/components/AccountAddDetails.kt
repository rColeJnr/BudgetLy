package com.rick.budgetly.feature_account.ui.accountneworedit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rick.budgetly.R

@Composable
fun AccountAddDetails(
    description: String,
    onDescriptionChange: (String) -> Unit,
    limit: String,
    onLimitChange: (String) -> Unit,
    balance: String,
    onBalanceChange: (String) -> Unit,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    type: String,
    onTypeChange: (Int) -> Unit,
    currency: String,
    onCurrencyChange: (Int) -> Unit
) {

    Surface(
        color = MaterialTheme.colors.background
    ) {
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceEvenly) {
            AccountTitleDetailColumn(
                title = "Type",
                detail = type
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            AccountTitleDetailColumn(
                title = "Currency",
                detail = currency
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            AccountTitleDetailColumn(
                title = "Description",
                detail = description
            )

            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            AccountTitleBalanceRow(title = "Credit Limit", balance = limit)
            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            AccountTitleBalanceRow(title = "Current Balance", balance = balance)
            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            AccountTitleOptionRow(title = "IncludeInTotal", boolean =checked , onCheckedChange = onCheckedChange)
        }
    }
}

@Composable
fun AccountTitleDetailColumn(title: String, detail: String) {
    Card(modifier = Modifier
        .clip(RoundedCornerShape(4.dp))
        .height(68.dp)
        .fillMaxWidth()
    ){
        Column(Modifier.fillMaxWidth()) {
            Text(text = title, style = MaterialTheme.typography.h5)
            Text(text = detail, style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
fun AccountTitleBalanceRow(title: String, balance: String) {
    Card(modifier = Modifier
        .clip(RoundedCornerShape(4.dp))
        .height(68.dp)
        .fillMaxWidth()
    ) {
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
            Text(text = title, style = MaterialTheme.typography.h5)
            Text(text = balance, style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
fun AccountTitleOptionRow(title: String, boolean: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Card(modifier = Modifier
        .clip(RoundedCornerShape(8.dp))
        .height(68.dp)
        .fillMaxWidth()
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = title, style = MaterialTheme.typography.h5)
            Switch(checked = boolean, onCheckedChange = onCheckedChange)
        }
    }
}

@Preview
@Composable
fun PreviewAccountAddDetails() {
    AccountAddDetails(
        description = "Account descript",
        onDescriptionChange = {},
        limit = "1434",
        onLimitChange = {},
        balance = "13454",
        onBalanceChange = {},
        checked = true,
        onCheckedChange = {},
        onTypeChange = {},
        onCurrencyChange = {},
        type = "TYPE",
        currency = "MZN"
    )
}