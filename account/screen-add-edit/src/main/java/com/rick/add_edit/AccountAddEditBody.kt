package com.rick.budgetly.feature_account.ui.accountneworedit

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rick.budgetly.BudgetLyContainer
import com.rick.budgetly.R
import com.rick.budgetly.components.BaseBottomSheet
import com.rick.budgetly.components.Calculator
import com.rick.budgetly.components.numero
import com.rick.budgetly.feature_account.domain.AccountColor
import com.rick.budgetly.feature_account.domain.AccountCurrency
import com.rick.budgetly.feature_account.domain.AccountIcon
import com.rick.budgetly.feature_account.domain.AccountType
import com.rick.budgetly.feature_account.ui.accountneworedit.components.AccountAddEditDetails
import com.rick.budgetly.feature_account.ui.accountneworedit.components.AnimatedColorsRow
import com.rick.budgetly.feature_account.ui.accountneworedit.components.AnimatedIconRow
import com.rick.budgetly.feature_account.ui.components.DefaultInputText
import com.rick.budgetly.feature_account.ui.util.TestTags
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccountAddEditBody(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: AccountAddEditViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is BudgetLyContainer.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is BudgetLyContainer.ShowSuccess ->
                    navController.navigateUp()
                else -> {}
            }
        }
    }

    val state = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    BaseBottomSheet(
        state = state,
        scope = scope,
        navController = navController,
        sheetContent = { Calculator(viewModel, state = state, scope = scope) }
    ) {
        ScreenContent (modifier, viewModel, navController, state, scope)
        if (!state.isVisible) numero.value = ""
    }
}

@ExperimentalMaterialApi
@Composable
private fun ScreenContent(
    modifier: Modifier,
    viewModel: AccountAddEditViewModel,
    navController: NavHostController,
    state: ModalBottomSheetState,
    scope: CoroutineScope,
) {
    val iconList = mutableListOf<ImageVector>()
    for (icon in AccountIcon.values()){
        iconList.add(icon.imageVector)
    }
    Column(
        modifier = modifier
            .fillMaxHeight(),
    ) {
        TopBarWithTextField(
            iconsVisible = viewModel.accountTitle.value.isNotEmpty(),
            text = viewModel.accountTitle.value,
            onTextChange = { viewModel.onEvent(AccountAddEditEvents.EnteredTitle(it)) },
            icon = AccountIcon.values()[viewModel.accountIcon.value].imageVector,
            iconList = iconList,
            onIconChange = { viewModel.onEvent(AccountAddEditEvents.ChangeAccountIcon(it)) },
            color = AccountColor.values()[viewModel.accountColor.value],
            onColorChange = { viewModel.onEvent(AccountAddEditEvents.ChangeAccountColor(it.color)) },
            onSaveAccount = {
                viewModel.onEvent(AccountAddEditEvents.SaveAccount)
            },
            onCancelAccount = {
                viewModel.onEvent(AccountAddEditEvents.CancelAccount)
                navController.navigateUp()
            }
        )
        AccountAddEditDetails(
            type = viewModel.accountType.value,
            onTypeChange = { viewModel.onEvent(AccountAddEditEvents.ChangeAccountType(AccountType.values()[it].type)) },
            currency = viewModel.accountCurrency.value,
            onCurrencyChange = {
                viewModel.onEvent(
                    AccountAddEditEvents.ChangeAccountCurrency(
                        AccountCurrency.values()[it].currency
                    )
                )
            },
            checked = viewModel.accountInTotalStatus.value,
            onCheckedChange = { viewModel.onEvent(AccountAddEditEvents.ChangeIncludeInTotalStatus(it)) },
            description = viewModel.accountDescription.value,
            onDescriptionChange = { viewModel.onEvent(AccountAddEditEvents.EnteredDescription(it)) },
            limit = viewModel.accountLimit.value,
//            onLimitChange = { viewModel.onEvent(AccountAddEditEvents.EnteredCreditLimit(it)) },
            balance = viewModel.accountBalance.value,
            scope = scope,
            state = state,
//            onBalanceChange = { viewModel.onEvent(AccountAddEditEvents.EnteredAccountBalance(it)) },
        )
    }
}

@Composable
fun TopBarWithTextField(
    iconsVisible: Boolean,
    text: String,
    onTextChange: (String) -> Unit,
    iconList: List<ImageVector>,
    icon: ImageVector,
    onIconChange: (Int) -> Unit,
    color: AccountColor,
    onColorChange: (AccountColor) -> Unit,
    onSaveAccount: () -> Unit,
    onCancelAccount: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = R.string.cancel),
                modifier = Modifier
                    .clickable { onCancelAccount() }
                    .size(24.dp)
                    .padding(start = 4.dp)
            )
            Text(text = stringResource(R.string.new_account), style = MaterialTheme.typography.h5)
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = stringResource(id = R.string.save),
                modifier = Modifier
                    .clickable { onSaveAccount() }
                    .size(24.dp)
                    .padding(end = 4.dp)
            )
        }
        DefaultInputText(
            text = text,
            onTextChange = onTextChange,
            testTag = TestTags.newAccountTitle
        )
        if (iconsVisible) {
            Column {
                // I should have just one animated row
                AnimatedIconRow(
                    iconList = iconList,
                    icon = icon,
                    onIconChange = onIconChange,
                    modifier = Modifier.padding(top = 8.dp)
                )
                // TODO to be implement after i complete base functionality of app
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

