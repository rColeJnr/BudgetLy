package com.rick.composables.myfirstcompose.main.account_screen.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rick.budgetly.feature_account.ui.accounts.components.AccountList
import com.rick.composables.myfirstcompose.domain.model.Account

@Composable
fun AccountScreen(accountList: List<Account>, modifier: Modifier = Modifier) {

    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        AccountList(accountList = accountList, Modifier.weight(1f))
        // I want to move this row to app bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "ACCOUNTS")
            Text(text = "$ 0.0")
        }
        TextButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Create account")
            Text(text = "Add account")
        }

    }

}

@Preview
@Composable
fun AccountScreenPreview() {
    val list = mutableListOf<Account>()
    for (i in 0..3) {
        list.add(
            Account(
                "account #$i",
                "Regular",
                "MZN",
                "${i * 7}",
                "1000",
                "Account $i description"
            )
        )
    }

    AccountScreen(accountList = list)
}