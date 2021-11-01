package com.rick.budgetly.feature_account.ui.accountneworedit

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rick.budgetly.R
import com.rick.budgetly.feature_account.domain.AccountColor
import com.rick.budgetly.feature_account.domain.AccountIcon
import com.rick.budgetly.feature_account.ui.accountneworedit.components.AccountAddDetails
import com.rick.budgetly.feature_account.ui.accountneworedit.components.AnimatedColorsRow
import com.rick.budgetly.feature_account.ui.accountneworedit.components.AnimatedIconRow
import com.rick.budgetly.feature_account.ui.accounts.components.AccountInputText

@ExperimentalAnimationApi
@Composable
fun AccountAddEditScreen(modifier: Modifier = Modifier) {

    val (text, onTextChange) = rememberSaveable{ mutableStateOf("")}
    val (description) = remember { mutableStateOf("") }
    val (limit) = remember { mutableStateOf("") }
    val (balance) = remember { mutableStateOf("") }
    val (checked, onCheckedChange) = remember { mutableStateOf(true) }
    val (icon, onIconChange) = remember { mutableStateOf(AccountIcon.Default) }
    val (color, onColorChange) = remember { mutableStateOf(AccountColor.Default)}

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxHeight()
    ) {
        TopBarWithTextField(
            iconsVisible = text.isNotBlank(),
            text = text,
            onTextChange = onTextChange,
            icon = icon,
            onIconChange = onIconChange,
            color = color,
            onColorChange = onColorChange,
            modifier = Modifier.fillMaxWidth()
        )
        AccountAddDetails(description = description, limit = limit, balance = balance, checked = checked, onCheckedChange = onCheckedChange)
    }

}

@ExperimentalAnimationApi
@Composable
fun TopBarWithTextField(
    iconsVisible: Boolean,
    text: String,
    onTextChange: (String) -> Unit,
    icon: AccountIcon,
    onIconChange: (AccountIcon) -> Unit,
    color: AccountColor,
    onColorChange: (AccountColor) -> Unit,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
            Text(text = stringResource(R.string.NEWACCOUNT), style = MaterialTheme.typography.h4)
            Icon(imageVector = Icons.Default.Check, contentDescription = null)
        }
        Spacer(modifier = modifier.height(8.dp))
        AccountInputText(text = text, onTextChange = onTextChange)
        Spacer(modifier = modifier.height(8.dp))
        if (iconsVisible) {
            Column(){
                AnimatedIconRow(
                    icon = icon,
                    onIconChange = onIconChange,
                    modifier = Modifier.padding(top = 8.dp)
                )
                AnimatedColorsRow(
                    colorsVisible = iconsVisible,
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
    AccountAddEditScreen()
}

