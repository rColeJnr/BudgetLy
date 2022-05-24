package com.rick.budgetly.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.rick.budgetly.feature_account.ui.accountneworedit.AccountAddEditEvents
import com.rick.budgetly.feature_account.ui.accountneworedit.AccountAddEditViewModel
import com.rick.budgetly.feature_account.ui.accountneworedit.components.field
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*
* lets sleep early, wake up early and go get us that ipad
* */

fun backSpace() {
    val length = numero.value.length
    if (length > 0) numero.value = numero.value.subSequence(0, length - 1).toString()
}

fun divide(num1: String, num2: String): String{
    if (num2 == "") return num1
    return if (num2.toFloat() != 0f){
        (dividir(num1.toFloat(), num2.toFloat())).toString()
    } else {
        "Can't divide by zero"
    }
}

fun multiply(num1: String, num2: String): String {
    if (num2 == "") return num1
    return (multiplicar(num1.toFloat(),num2.toFloat())).toString()
}
fun add(num1: String, num2: String): String{
    if (num2 == "") return num1
    return (adicionar(num1.toFloat(),num2.toFloat())).toString()
}
fun subtract(num1: String, num2: String): String {
    if (num2 == "") return num1
    return (subtrair(num1.toFloat(),num2.toFloat())).toString()
}

fun canCalculate(num1: String?, num2: String?): Boolean {
    return if (num1 != null && num2 == null) true
    else return (num1 != null && num2 != null)
}

fun calculate(num1: String, num2: String?): String {
    if (num2 == null) return numero.value
    when (operator) {
        "+" -> return add(num1, num2)
        "-" -> return subtract(num1, num2)
        "*" -> return multiply(num1, num2)
        "/" -> return divide(num1, num2)
    }
    return numero.value
}

private fun adicionar(num1: Float, num2: Float) = num1 + num2
private fun subtrair(num1: Float, num2: Float) = num1 - num2
private fun multiplicar(num1: Float, num2: Float) = num1 * num2
private fun dividir(num1: Float, num2: Float) = num1 / num2

val operators = listOf(
    "+", "-", "*", "/"
)

// We do it like apple calculator
// clean the screen after sub, or add, or else

private var canAddOperation = false
private var canChangeOperation = false
private var canAddDecimal = true
private var setResult = false
private var num1: String? = null
private var num2: String? = null
var numero: MutableState<String> = mutableStateOf("")
private var operator: String = ""

fun numberAction(number: String){
    if(number == "."){
        if (canAddDecimal) numero.value += number; canAddDecimal = false
    }
    if (number in operators){
        operationAction(number)
        canChangeOperation(number)
    }
    if (number == "<"){
        backSpace()
    }
    if (number == "="){
        if (operator.isNotEmpty()) num2 = numero.value
        else num1 = numero.value
        if (canCalculate(num1, num2)) {
            numero.value = calculate(num1 = num1!!, num2 = num2)
            operator = ""
            setResult = true
        }

    }
    if (number in lilst && number != "<" && number != "."){
        numero.value += number
        canAddOperation = true
        canChangeOperation = false
    }
}

fun operationAction(operation: String){
    if (canAddOperation){
        num1 = numero.value
        operator = operation
        numero.value = ""
        canAddOperation = false
        canChangeOperation = true
        canAddDecimal = true
    }
}

fun canChangeOperation(newOperator: String){
    if (canChangeOperation && numero.value.isEmpty()) operator = newOperator
}


val lilst = listOf(
    "7", "4", "1", ".", "8", "5", "2", "0", "9", "6", "3", "<"
)
val list = listOf(
    "+", "-", "*", "/", "="
)

@ExperimentalMaterialApi
@Composable
fun Calculator(viewModel: ViewModel? = null, state: ModalBottomSheetState, scope: CoroutineScope) {
    Column(Modifier.fillMaxWidth()) {
        numero = remember{ mutableStateOf("") }
        Text(text = numero.value, modifier = Modifier.padding(start = 65.dp))
        CustomRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.Start),
            rows = 1,
            onClick = {}
        ) {
            for (i in list) {
                TextButton(
                    onClick = {
                        numberAction(i)
                        if (setResult){
                            if (viewModel is AccountAddEditViewModel){
                                viewModel.apply {
                                    if (field == "l"){
                                        viewModel.onEvent(AccountAddEditEvents.EnteredCreditLimit(numero.value))
                                    }
                                    if (field == "b"){
                                        viewModel.onEvent(AccountAddEditEvents.EnteredAccountBalance(numero.value))
                                    }
                                }
                            }
//                            scope.launch { state.animateTo(ModalBottomSheetValue.Hidden, SwipeableDefaults.AnimationSpec) }
                        }
                    }, modifier = Modifier
                        .widthIn(min = 65.dp, max = 800.dp)
                ) {
                    Text(text = i)
                }
            }
        }
        CustomRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.Start),
            onClick = {}
        ) {
            for (i in lilst) {
                TextButton(
                    onClick = {
                        numberAction(i)
                    },
                    modifier = Modifier
                        .widthIn(min = 100.dp, max = 800.dp)
                ) {
                    Text(text = i)
                }
            }
        }
    }
}