package com.rick.budgetly.feature_account.ui.accountneworedit.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MergeType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rick.budgetly.R
import com.rick.budgetly.components.TextDropdownMenu
import com.rick.budgetly.feature_account.domain.AccountCurrency
import com.rick.budgetly.feature_account.domain.AccountType
import com.rick.budgetly.feature_account.ui.components.DefaultInputText
import com.rick.budgetly.feature_account.ui.util.TestTags
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun AccountAddEditDetails(
    description: String,
    onDescriptionChange: (String) -> Unit,
    limit: String,
    balance: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    type: String,
    onTypeChange: (Int) -> Unit,
    currency: String,
    onCurrencyChange: (Int) -> Unit,
    scope: CoroutineScope,
    state: ModalBottomSheetState,
) {

    val context = LocalContext.current

    Surface(
        color = MaterialTheme.colors.background
    ) {
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceEvenly) {
            DefaultInputText(
                text = description,
                onTextChange = onDescriptionChange,
                label = stringResource(R.string.description),
                testTag = TestTags.newAccountDescription,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            AccountTitleDetailColumn(
                title = stringResource(R.string.account_type),
                icon = Icons.Default.MergeType,
                detail = {
                    TextDropdownMenu(
                        text = type,
                        items = typeList(),
                        onMenuItemClick = {
                            onTypeChange(it)
                        }
                    )
                },
                onClick = { }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            AccountTitleDetailColumn(
                title = stringResource(R.string.account_currency),
                icon = Icons.Default.MergeType,
                detail = {
                    TextDropdownMenu(
                        text = currency,
                        items = currencyList(),
                        onMenuItemClick = {
                            onCurrencyChange(it)
                        }
                    )
                },
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            AccountTitleDetailColumn(
                title = stringResource(R.string.account_limit),
                icon = Icons.Default.MergeType,
                detail = {
                    Text(text = limit, modifier = Modifier.padding(start = 16.dp))
                },
                // TODO replace/clean this field code
                onClick = { scope.launch { state.show() }; field = "l" }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            AccountTitleDetailColumn(
                title = stringResource(R.string.account_balance),
                icon = Icons.Default.MergeType,
                detail = {
                    Text(text = balance, modifier = Modifier.padding(start = 16.dp))
                },
                onClick = { scope.launch { state.show() }; field = "b" }
            )

            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            AccountTitleOptionRow(
                title = stringResource(R.string.account_include_in_total),
                boolean = checked,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

var field = ""

@Composable
fun AccountTitleDetailColumn(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    detail: @Composable () -> Unit = {},
    onClick: () -> Unit = {}
) {

    Card(modifier = modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .clickable { onClick() }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = title, style = MaterialTheme.typography.h6)
                detail()
            }
            Icon(imageVector = icon, contentDescription = null)
        }
    }
}

@Composable
fun AccountTitleOptionRow(title: String, boolean: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Card(modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .clickable { onCheckedChange(!boolean) }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, style = MaterialTheme.typography.h6)
            Switch(checked = boolean, onCheckedChange = onCheckedChange)
        }
    }
}

fun currencyList(): List<String> {
    val currencies: MutableList<String> = mutableListOf()
    for (value in AccountCurrency.values().toList()) {
        currencies.add(value.name)
    }
    return currencies
}

fun typeList(): List<String> {
    val types: MutableList<String> = mutableListOf()
    for (value in AccountType.values().toList()) {
        types.add(value.name)
    }
    return types
}