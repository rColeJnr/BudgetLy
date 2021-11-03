package com.rick.budgetly.feature_account.ui.accountneworedit

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rick.budgetly.R
import com.rick.budgetly.feature_account.domain.AccountColor
import com.rick.budgetly.feature_account.domain.AccountCurrency
import com.rick.budgetly.feature_account.domain.AccountIcon
import com.rick.budgetly.feature_account.domain.AccountType
import com.rick.budgetly.feature_account.ui.accountneworedit.components.AccountAddDetails
import com.rick.budgetly.feature_account.ui.accountneworedit.components.AnimatedColorsRow
import com.rick.budgetly.feature_account.ui.accountneworedit.components.AnimatedIconRow
import com.rick.budgetly.feature_account.ui.accounts.components.AccountInputText
import com.rick.budgetly.feature_account.ui.accounts.components.AccountTopBar

@ExperimentalAnimationApi
@Composable
fun AccountAddEditScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: AccountAddEditViewModel
) {

    // dude will get here with an account from navController
    viewModel.onStart(null)

    val onCancelAccount = {
        navController.navigateUp()
        viewModel.currentAccount = null
    }

    val onSaveAccount = {
        navController.navigateUp()
        viewModel.onEvent(AccountAddEditEvents.SaveAccount)
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxHeight(),
    ) {
        TopBarWithTextField(
            iconsVisible = viewModel.accountTitle.value.isNotEmpty(),
            text = viewModel.accountTitle.value,
            onTextChange = { viewModel.onEvent(AccountAddEditEvents.EnteredTitle(it)) },
            icon = AccountIcon.values()[viewModel.accountIcon.value],
            // there's a fix for this, same way we got the icon in state code lab
            // tomorrow, but the principle is that we get the exact clicked icon from  the ui
            // and we then convert it to int and stuff. i guess.
            onIconChange = { viewModel.onEvent(AccountAddEditEvents.ChangeAccountIcon(it)) },
            color = AccountColor.values()[viewModel.accountColor.value],
            // we do whaever we do for Icon for color too
            onColorChange = { viewModel.onEvent(AccountAddEditEvents.ChangeAccountColor(it.color)) },
            modifier = Modifier.fillMaxWidth(),
            onCancelAccount = onCancelAccount,
            onSaveAccount = onSaveAccount
        )
        AccountAddDetails(
            description = viewModel.accountDescription.value,
            limit = viewModel.accountLimit.value,
            balance = viewModel.accountBalance.value,
            checked = viewModel.accountInTotalStatus.value,
            type = viewModel.accountType.value,
            currency = viewModel.accountCurrency.value,
            onCheckedChange = { viewModel.onEvent(AccountAddEditEvents.ChangeIncludeInTotalStatus(it)) },
            onBalanceChange = { viewModel.onEvent(AccountAddEditEvents.EnteredAccountBalance(it)) },
            onCurrencyChange = { viewModel.onEvent(AccountAddEditEvents.ChangeAccountCurrency(AccountCurrency.values()[it].currency))},
            onDescriptionChange = { viewModel.onEvent(AccountAddEditEvents.EnteredDescription(it)) },
            onLimitChange = { viewModel.onEvent(AccountAddEditEvents.EnteredCreditLimit(it)) },
            onTypeChange = { viewModel.onEvent(AccountAddEditEvents.ChangeAccountType(AccountType.values()[it].type))},
        )
    }

}

@ExperimentalAnimationApi
@Composable
fun TopBarWithTextField(
    iconsVisible: Boolean,
    text: String,
    onTextChange: (String) -> Unit,
    icon: AccountIcon,
    onIconChange: (Int) -> Unit,
    color: AccountColor,
    onColorChange: (AccountColor) -> Unit,
    onSaveAccount: () -> Unit,
    onCancelAccount: () -> Unit,
    modifier: Modifier
) {
    Column() {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier
                    .clickable { onCancelAccount() }
                    .height(24.dp)
            )
            Text(text = stringResource(R.string.NEWACCOUNT), style = MaterialTheme.typography.h5)
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                modifier = Modifier
                    .clickable { onSaveAccount() }
                    .height(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        AccountInputText(text = text, onTextChange = onTextChange)
        Spacer(modifier = Modifier.height(8.dp))
        if (iconsVisible) {
            Column() {
                // I should have just one animated row
                AnimatedIconRow(
                    icon = icon,
                    onIconChange = onIconChange,
                    modifier = Modifier.padding(top = 8.dp)
                )
                AnimatedColorsRow(
                    colorsVisible = false,
                    color = color,
                    onColorChange = onColorChange
                )
            }
        } else {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewAccountTopBar() {
    Column {
        TopBarWithTextField(
            iconsVisible = true,
            text = "Text",
            onTextChange = {},
            icon = AccountIcon.Default,
            onIconChange = {},
            color = AccountColor.Default,
            onColorChange = {},
            onSaveAccount = { /*TODO*/ },
            onCancelAccount = { /*TODO*/ },
            modifier = Modifier
        )
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
}

