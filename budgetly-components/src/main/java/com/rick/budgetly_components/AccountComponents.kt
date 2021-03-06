package com.rick.budgetly_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.rick.core.theme.toolBar

@Composable
fun AccountTopBar(
    modifier: Modifier,
    title: String,
    secondTitle: String,
    iconRow: @Composable () -> Unit
) {
    TopAppBar(
        modifier = modifier,
//        backgroundColor = MaterialTheme.colors.background,
        contentColor = Color.White,
        title = {
            Column{
                Text(text = title, style = toolBar.body1, textAlign = TextAlign.Start)
                Text(text = secondTitle, style = toolBar.body2, fontWeight = FontWeight.Light, textAlign = TextAlign.Start)
            }
        },
        actions ={
            iconRow()
        }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DefaultInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String = "",
    onTextChange: (String) -> Unit,
    singleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    testTag: String = "",
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        label = { Text(text = label) },
        singleLine = singleLine,
        keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier.fillMaxWidth().testTag(testTag)
    )
}