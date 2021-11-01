package com.rick.budgetly.feature_account.ui.accountdetails

import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun AccountDetailsScreen(
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = modifier,
                backgroundColor = Color.White,
                title = {},
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                },
                actions = {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                    Spacer(modifier = modifier.width(8.dp))
                    Icon(imageVector = Icons.Default.MenuOpen, contentDescription = null)
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.AddBusiness, contentDescription = null)
            }
        }
    ) {
        Column(
            modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = modifier.height(32.dp))
            Icon(imageVector = Icons.Filled.AccountBalanceWallet, contentDescription = null, modifier.size(75.dp))
            Spacer(modifier = modifier.height(16.dp))
            Text(text = "Account name")
            Spacer(modifier = modifier.height(16.dp))
            Text(text = "$2000")
            Spacer(modifier = modifier.height(8.dp))
            Text(text = "Balance")
            Spacer(modifier = modifier.height(24.dp))
            Text(text = "Totals for month")
            Spacer(modifier = modifier.height(16.dp))
            Row (horizontalArrangement = Arrangement.SpaceEvenly){
                AccountIncomeExpenses(modifier = modifier, text = "Income", money = "$2000", imageVector = Icons.Default.ArrowCircleDown)
                Spacer(modifier = modifier.width(50.dp))
                AccountIncomeExpenses(modifier = modifier, text = "Expenses", money = "$2000", imageVector = Icons.Default.ArrowCircleUp)
            }
        }

    }
}

@Composable
fun AccountIncomeExpenses(modifier: Modifier, text: String, money: String, imageVector: ImageVector) {
    Box(
        modifier = modifier
            .width(175.dp)
            .height(95.dp)
            .border(width = 2.dp, color = LightGray, shape = RectangleShape)
            .clip(MaterialTheme.shapes.large)
            .padding(16.dp)
    ) {
        ConstraintLayout(modifier = modifier.fillMaxSize()) {
            val (title, amount, icon) = createRefs()

            Text(text = text,
                modifier
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                    }
                    .padding(start = 16.dp, top = 16.dp))
            Text(text = money, modifier = modifier.constrainAs(amount) {
                top.linkTo(title.bottom)
            }.padding(start = 16.dp, top = 8.dp)
            )
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                modifier = modifier.constrainAs(icon) { top.linkTo(parent.top); end.linkTo(parent.end) }.padding(8.dp))
        }
    }
}

@Preview
@Composable
fun PreviewAccountDetailsScreen() {
    AccountDetailsScreen()
}