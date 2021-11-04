package com.rick.budgetly.feature_account.ui.accountdetails

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.rick.budgetly.components.BaseDropdownMenu
import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.AccountIcon
import com.rick.budgetly.feature_account.ui.util.dollarSign
import com.rick.budgetly.feature_account.ui.util.formatAmount

@Composable
fun AccountDetailsBody(
    modifier: Modifier = Modifier,
    serializableAccount: Account,
    viewModel: AccountDetailsViewModel,
    onNewAccountClick: () -> Unit,
    onNavigationIconClick: () -> Unit,
    onSettingsClick: (Account) -> Unit,
    onDeleteClick: () -> Unit
) {

    viewModel.currentAccount = serializableAccount
    val account = viewModel.currentAccount!!

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
                        contentDescription = null,
                        modifier = Modifier.clickable { onNavigationIconClick() }
                    )
                },
                actions = {
                    IconButton(onClick = { onSettingsClick(account) }){ Icon(imageVector = Icons.Default.Settings, contentDescription = null) }
                    Spacer(modifier = modifier.width(8.dp))
                    DetailsDropDownMenu(
                        icon = Icons.Default.MoreVert,
                        onMenuItemOneClick = { viewModel.onEvent( AccountDetailsEvents.ChangeMainStatus) },
                        menuItemOneContent = { Text(text = "Set as main account") },
                        onMenuItemSecondClick = { viewModel.onEvent( AccountDetailsEvents.ChangeIncludeInTotalStatus) },
                        menuItemSecondContent = { Text(text = if (account.include) "Don't inclute in total" else "Include in total" ) },
                        onMenuItemThirdClick = { onDeleteClick() },
                        menuItemThirdContent = { Text(text = "Delete account") }
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onNewAccountClick() }) {
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
            //theme the god damn app
            val balance = account.balance.toFloat()
            Spacer(modifier = modifier.height(32.dp))
            Icon(imageVector = AccountIcon.values()[account.icon].imageVector, contentDescription = AccountIcon.values()[account.icon].contentDescription, modifier.size(75.dp))
            Spacer(modifier = modifier.height(16.dp))
            Text(text = account.title)
            Spacer(modifier = modifier.height(16.dp))
            Text(text = dollarSign(balance) + formatAmount(balance), style = MaterialTheme.typography.h5)
            Spacer(modifier = modifier.height(8.dp))
            Text("Balance", style = MaterialTheme.typography.body2)
            Spacer(modifier = modifier.height(24.dp))
            Text(text = "Totals for month")
            Spacer(modifier = modifier.height(16.dp))
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                AccountIncomeExpenses(modifier = Modifier, text = "Income", money = "$ Todo", imageVector = Icons.Default.ArrowCircleDown)
                Spacer(modifier = Modifier.width(8.dp))
                AccountIncomeExpenses(modifier = Modifier, text = "Outcome", money = "$ Todo", imageVector = Icons.Default.ArrowCircleUp)
            }
        }

    }
}

@Composable
fun AccountIncomeExpenses(modifier: Modifier, text: String, money: String, imageVector: ImageVector) {
    Box(
        modifier = modifier
            .border(width = 2.dp, color = LightGray, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        ConstraintLayout(modifier = modifier
            .wrapContentHeight()
            .width(120.dp)
        ) {
            val (title, amount, icon) = createRefs()

            Text(text = text,
                modifier
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                    }
                    .padding(start = 8.dp, top = 8.dp)
            )
            Text(text = money, modifier = modifier
                .constrainAs(amount) {
                    top.linkTo(title.bottom)
                }
                .padding(start = 8.dp, top = 8.dp)
            )
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                modifier = modifier
                    .constrainAs(icon) { top.linkTo(title.top); start.linkTo(title.end) }
                    .padding(top = 8.dp, start = 8.dp),
            )
        }
    }
}

@Composable
fun DetailsDropDownMenu(
    icon: ImageVector,
    onMenuItemOneClick: () -> Unit,
    menuItemOneContent: @Composable () -> Unit,
    onMenuItemSecondClick: () -> Unit,
    menuItemSecondContent: @Composable () -> Unit,
    onMenuItemThirdClick: () -> Unit,
    menuItemThirdContent: @Composable () -> Unit,
) {
    BaseDropdownMenu(
        icon = icon,
        onMenuItemOneClick = { onMenuItemOneClick() },
        menuItemOneContent = { menuItemOneContent() },
        onMenuItemSecondClick = { onMenuItemSecondClick() },
        menuItemSecondContent = { menuItemSecondContent() },
        onMenuItemThirdClick = { onMenuItemThirdClick() },
        menuItemThirdContent = { menuItemThirdContent() }
    )
}

@Preview
@Composable
fun PreviewAccountDetailsScreen() {
//    AccountDetailsBody()
}
