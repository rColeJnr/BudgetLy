package com.rick.screen_transactions.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rick.screen_transactions.R
import java.util.*

val transactions = listOf(
    com.rick.data_transactions.domain.Transaction(
        com.rick.data_transactions.domain.TransactionType.DEPOSIT.name,
        Calendar.getInstance(),
        "ricardo",
        "junior",
        4,
        0
    ),
    com.rick.data_transactions.domain.Transaction(
        com.rick.data_transactions.domain.TransactionType.PAYMENT.name,
        Calendar.getInstance(),
        "rcole",
        "junior",
        45,
        1
    ),
    com.rick.data_transactions.domain.Transaction(
        com.rick.data_transactions.domain.TransactionType.TRANSFER.name,
        Calendar.getInstance(),
        "me now",
        "me future",
        4054,
        2
    ),
    com.rick.data_transactions.domain.Transaction(
        com.rick.data_transactions.domain.TransactionType.WITHDRAW.name,
        Calendar.getInstance(),
        "me now",
        "me future",
        454,
        3
    ),
)

@Composable
fun TransactionsBody(navController: NavHostController) {

    Column(verticalArrangement = Arrangement.SpaceBetween) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.transactions)) },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            actions = {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    imageVector = Icons.Default.Edit,
                    contentDescription = "edit icon"
                )
            },
        )

        LazyColumn {
            items(transactions) {
                TransactionRow(transaction = it)
            }
        }
    }

}

@Composable
fun TransactionRow(transaction: com.rick.data_transactions.domain.Transaction) {

    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(
                width = 2.dp,
                color = Color.DarkGray,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Column(modifier = Modifier) {
                Text(text = "${transaction.sender} -> ${transaction.receiver}")
                Text(text = "12.05 ${transaction.type}")
            }
            Text(text = "${transaction.amount} $")
        }
    }

}

