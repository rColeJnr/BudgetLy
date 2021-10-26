package com.rick.budgetly.feature_account.ui.accounts.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.R


@Composable
fun AccountList(accountList: List<Account>, modifier: Modifier = Modifier){

    LazyColumn(state = rememberLazyListState(), modifier = modifier) {
        items(accountList){ account ->
            AccountItem(account = account)
        }
    }
}

@Composable
fun AccountItem(account: Account, modifier: Modifier = Modifier){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(
            modifier = Modifier
                .padding(8.dp),
        ) {
            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground) , contentDescription = "somehing")
        }
        Column {
            Text(text = account.name)
            Text(text = account.balance)
            Text(text = account.currency)
        }
        Column {
            Text(text = account.type, fontWeight = FontWeight.Light)
            Text(text = account.limit, fontWeight = FontWeight.Light)
        }
    }
}

@Preview
@Composable
fun PreviewAccountItem() {
    val account = Account("account name", "Regular", "MZN", "$123", "1000", "Account description", "")
    AccountItem(account)
}

@Preview
@Composable
fun PreviewAccountList() {
    val list = mutableListOf<Account>()
    for (i in 0..8){
        list.add(Account("account #$i", "Regular", "MZN", "${i * 7}", "1000", "Account $i description", ""))
    }

    AccountList(list)
}