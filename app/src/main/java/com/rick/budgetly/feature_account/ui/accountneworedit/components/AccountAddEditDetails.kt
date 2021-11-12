package com.rick.budgetly.feature_account.ui.accountneworedit.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rick.budgetly.components.Calculator
import com.rick.budgetly.components.TextDropdownMenu
import com.rick.budgetly.feature_account.domain.AccountCurrency
import com.rick.budgetly.feature_account.domain.AccountType
import com.rick.budgetly.feature_account.ui.components.AccountInputText
import kotlinx.coroutines.launch

val lilst = listOf(
    "7", "4", "1", ".", "8", "5", "2", "0", "9", "6", "3", "<"
)
val list = listOf(
    "+", "-", "x", "/", "Ok"
)

@ExperimentalMaterialApi
@Composable
fun AccountAddEditDetails(
    description: String,
    onDescriptionChange: (String) -> Unit,
    limit: String,
    onLimitChange: (String) -> Unit,
    balance: String,
    onBalanceChange: (String) -> Unit,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    type: String,
    onTypeChange: (Int) -> Unit,
    currency: String,
    onCurrencyChange: (Int) -> Unit
) {

    val context = LocalContext.current
    val state =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val click = {Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()}
    ModalBottomSheetLayout(
        modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally),
        sheetState = state,
        sheetContent = {
            Calculator()
        },
    ) {
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceEvenly) {
            AccountTitleDetailColumn(
                title = "Type",
                icon = Icons.Default.MergeType,
                detail = {
                    TextDropdownMenu(
                        text = AccountType.Default.type,
                        items = typeList(),
                        onMenuItemClick = {
                            Toast.makeText(context, "$it", Toast.LENGTH_LONG).show()
                        }
                    )
                },
                onClick = { }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            AccountTitleDetailColumn(
                title = "Currency",
                icon = Icons.Default.MergeType,
                detail = {
                    TextDropdownMenu(
                        text = AccountCurrency.Default.currency,
                        items = currencyList(),
                        onMenuItemClick = {
                            Toast.makeText(context, "$it", Toast.LENGTH_LONG).show()
                        }
                    )
                },
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            AccountTitleDetailColumn(
                title = "Description",
                icon = Icons.Default.Description,
                detail = {
                    AccountInputText(
                        text = description,
                        onTextChange = onDescriptionChange,

                    )
                },
                onClick = { scope.launch { state.show() } }
            )

            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            AccountTitleDetailColumn(
                title = "Account Limit",
                icon = Icons.Default.AttachMoney,
                detail = {
                    Text(text = "$100000", modifier = Modifier.height(33.dp)) },
                onClick = { scope.launch { state.show() } }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            AccountTitleDetailColumn(
                title = "Current Balance",
                icon = Icons.Default.AttachMoney,
                detail = {
                    Text(text = "click me", modifier = Modifier.height(33.dp)) },
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            AccountTitleOptionRow(
                title = "IncludeInTotal",
                boolean = checked,
                onCheckedChange = onCheckedChange
            )
        }
    }

//    Surface(
//        color = MaterialTheme.colors.background
//    ) {
//
//    }
}



@Composable
fun AccountTitleDetailColumn(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    detail: @Composable () -> Unit = {},
    onClick: () -> Unit
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