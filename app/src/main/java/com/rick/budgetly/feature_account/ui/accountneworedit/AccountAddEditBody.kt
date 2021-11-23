package com.rick.budgetly.feature_account.ui.accountneworedit

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rick.budgetly.R
import com.rick.budgetly.feature_account.common.AccountsScreen
import com.rick.budgetly.feature_account.domain.*
import com.rick.budgetly.feature_account.ui.accountneworedit.components.AccountAddEditDetails
import com.rick.budgetly.feature_account.ui.accountneworedit.components.AnimatedColorsRow
import com.rick.budgetly.feature_account.ui.accountneworedit.components.AnimatedIconRow
import com.rick.budgetly.feature_account.ui.components.AccountInputText
import com.rick.budgetly.feature_account.ui.util.dummyAccounts

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccountAddEditBody(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: AccountAddEditViewModel = hiltViewModel()) {

    Column(
        modifier = modifier
            .fillMaxHeight(),
    ) {
        TopBarWithTextField(
            iconsVisible = viewModel.accountTitle.value.isNotEmpty(),
            text = viewModel.accountTitle.value,
            onTextChange = { viewModel.onEvent(AccountAddEditEvents.EnteredTitle(it)) },
            icon = AccountIcon.values()[viewModel.accountIcon.value],
            onIconChange = { viewModel.onEvent(AccountAddEditEvents.ChangeAccountIcon(it)) },
            color = AccountColor.values()[viewModel.accountColor.value],
            onColorChange = { viewModel.onEvent(AccountAddEditEvents.ChangeAccountColor(it.color)) },
            onSaveAccount = {
                viewModel.onEvent(AccountAddEditEvents.SaveAccount)
                navController.navigateUp()
            },
            onCancelAccount = {
                viewModel.onEvent(AccountAddEditEvents.CancelAccount)
                navController.navigateUp()
            }
        )
        AccountAddEditDetails(
            type = viewModel.accountType.value,
            onTypeChange = { viewModel.onEvent(AccountAddEditEvents.ChangeAccountType(AccountType.values()[it].type))},
            currency = viewModel.accountCurrency.value,
            onCurrencyChange = { viewModel.onEvent(AccountAddEditEvents.ChangeAccountCurrency(AccountCurrency.values()[it].currency))},
            checked = viewModel.accountInTotalStatus.value,
            onCheckedChange = { viewModel.onEvent(AccountAddEditEvents.ChangeIncludeInTotalStatus(it)) },
            description = viewModel.accountDescription.value,
            onDescriptionChange = { viewModel.onEvent(AccountAddEditEvents.EnteredDescription(it)) },
            limit = viewModel.accountLimit.value,
            onLimitChange = { viewModel.onEvent(AccountAddEditEvents.EnteredCreditLimit(it)) },
            balance = viewModel.accountBalance.value,
            onBalanceChange = { viewModel.onEvent(AccountAddEditEvents.EnteredAccountBalance(it)) },
        )
    }

}

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
    onCancelAccount: () -> Unit
) {
    Column {
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
            Column {
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

@OptIn(ExperimentalMaterialApi::class)
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
            onSaveAccount = {},
            onCancelAccount = {}
        )
        AccountAddEditDetails(
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

