package com.rick.budgetly.feature_account.ui.accounts.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rick.budgetly.R
        
@Composable
fun NewAccountScreen() {

    // New account title will go to top bar
    val (name, onNameChange) = rememberSaveable { mutableStateOf("") }
    val (description, onDescriptionChange) = rememberSaveable { mutableStateOf("") }
    val (balance, onBalanceChange) = rememberSaveable { mutableStateOf("") }
    val (limit, onLimitChange) = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        // New account
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .wrapContentHeight()
        ) {
            Column() {
                Text(
                    text = "New account",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(4.dp))

                AccountTextField(
                    text = name,
                    label = "name",
                    onTextChange = onNameChange,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions.Default
                )

            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Account details
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.25f)
                .wrapContentHeight()
        ) {
            Column {
                Text(
                    text = "Type",
                    fontWeight = FontWeight.Black,
                    style = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.height(26.dp))

                Text(
                    text = "Currency",
                    fontWeight = FontWeight.Black,
                    style = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.height(26.dp))

                AccountTextField(
                    text = description,
                    label = "description",
                    onTextChange = onDescriptionChange,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions.Default
                )

            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Account balance
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.25f)
                .wrapContentHeight()
        ) {
            Column {
                Text(
                    text = "Balance",
                    fontWeight = FontWeight.Black,
                    style = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Account Balance")
                    Text(text = "$ 0.0")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Account Limit")
                    Text(text = "$ 0.0")
                }

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "include in total balance")
                    Switch(checked = false, onCheckedChange = {})
                }
            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        // Account save/cancel buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                    Color.Magenta, Color.White
                ), shape = CircleShape, modifier = Modifier.padding(start = 16.dp, top = 8.dp)
            ){
                Text(text = "Save")
            }
            TextButton(
                onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                    Color.Blue, Color.White
                ), shape = CircleShape, modifier = Modifier.padding(end = 16.dp, top = 8.dp)
            ){
                Text(text = "Cancel")
            }
        }

    }

}

@Composable
fun AccountTextField(
    text: String,
    label: String,
    onTextChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text(text = label) },
        shape = CircleShape,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

@Preview
@Composable
fun NewAccountScreenPreview() {
    NewAccountScreen()
}