package com.rick.add_edit.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rick.budgetly_components.DefaultInputText
import com.rick.budgetly_components.DefaultRow
import com.rick.budgetly_components.TextDropdownMenu
import com.rick.data.AccountCurrency
import com.rick.data.AccountType
import com.rick.screen_add_edit.R
import com.rick.util.TestTags
import kotlinx.coroutines.CoroutineScope

@ExperimentalMaterialApi
@Composable
fun AccountAddEditDetails(
    description: String,
    onDescriptionChange: (String) -> Unit,
    limit: String,
    onLimitClick: (String) -> Unit,
    balance: String,
    onBalanceClick: (String) -> Unit,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    type: String,
    onTypeChange: (Int) -> Unit,
    currency: String,
    onCurrencyChange: (Int) -> Unit,
    scope: CoroutineScope,
    state: ModalBottomSheetState,
) {

//    val context = LocalContext.current

    Surface(
        color = MaterialTheme.colors.background
    ) {
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceEvenly) {

            DefaultInputText(
                text = description,
                onTextChange = { onDescriptionChange(it) },
                label = stringResource(R.string.description),
                testTag = TestTags.newAccountDescription,
            )
            Spacer(modifier = Modifier.height(8.dp))

            DefaultRow(
                text = stringResource(R.string.account_type),
                image = Icons.Default.Payments,
                description = stringResource(R.string.account_type),
                element = {
                    TextDropdownMenu(
                        text = type,
                        items = typeList(),
                        onMenuItemClick = {
                            onTypeChange(it)
                        }
                    )
                },
                padding = ROW_PADDING,
                onClick = { }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            DefaultRow(
                text = stringResource(R.string.account_currency),
                image = Icons.Rounded.Euro,
                description = stringResource(R.string.account_currency),
                element = {
                    TextDropdownMenu(
                        text = currency,
                        items = currencyList(),
                        onMenuItemClick = {
                            onCurrencyChange(it)
                        }
                    )
                },
                padding = ROW_PADDING,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            DefaultRow(
                text = stringResource(R.string.account_limit),
                image = Icons.Rounded.PriorityHigh,
                description = stringResource(R.string.account_limit),
                element = {
                    Text(text = limit)
                },
                onClick = { onLimitClick(limit) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            DefaultRow(
                text = stringResource(R.string.account_balance),
                image = Icons.Rounded.Paid,
                description = stringResource(R.string.account_balance),
                element = {
                    Text(text = balance)
                },
                onClick = { onBalanceClick(balance) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            DefaultRow(
                text = stringResource(R.string.account_include_in_total),
                image = Icons.Rounded.SwitchLeft,
                description = stringResource(R.string.account_include_in_total),
                element = {
                    Switch(checked = checked, onCheckedChange = { onCheckedChange(it) })
                },
                padding = ROW_PADDING,
                onClick = {}
            )
        }
    }
}

var field = ""
private val ROW_PADDING = 1.dp

@Composable
fun AccountTitleDetailColumn(modifier: Modifier = Modifier, title: String, icon: ImageVector, detail: @Composable () -> Unit = {}, onClick: () -> Unit = {}
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

@Preview
@Composable
fun PrevAddEditDetails() {

    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceEvenly) {
        DefaultInputText(
            text = "description",
            onTextChange = { },
            label = stringResource(R.string.description),
            testTag = TestTags.newAccountDescription,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider()

        DefaultRow(
            text = stringResource(R.string.account_type),
            image = Icons.Rounded.Payments,
            description = stringResource(R.string.account_type),
            element = {
                Text(text = "type")
            },
            onClick = { }
        )
        Spacer(modifier = Modifier.height(8.dp))

        DefaultRow(
            text = stringResource(R.string.account_currency),
            image = Icons.Rounded.Euro,
            description = stringResource(R.string.account_currency),
            element = {
                Text(text = "Currency")
            },
            onClick = {}
        )
        Spacer(modifier = Modifier.height(8.dp))

        DefaultRow(
            text = stringResource(R.string.account_limit),
            image = Icons.Rounded.PriorityHigh,
            description = stringResource(R.string.account_limit),
            element = {
                Text(text = "limit")
            },
            onClick = {  }
        )
        Spacer(modifier = Modifier.height(8.dp))

        DefaultRow(
            text = stringResource(R.string.account_balance),
            image = Icons.Rounded.Paid,
            description = stringResource(R.string.account_balance),
            element = {
                Text(text = "balance")
            },
            onClick = {  }
        )
        Spacer(modifier = Modifier.height(8.dp))

        DefaultRow(
            text = stringResource(R.string.account_include_in_total),
            image = Icons.Rounded.SwitchLeft,
            description = stringResource(R.string.account_include_in_total),
            element = {
                Switch(checked = true, onCheckedChange = {  })
            },
            onClick = {}
        )
    }
}