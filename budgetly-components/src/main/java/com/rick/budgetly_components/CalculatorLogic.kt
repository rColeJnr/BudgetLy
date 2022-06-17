package com.rick.budgetly_components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
    if (number in Numeros.values().toString() && number != "<" && number != "."){
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

enum class Sinais (val sinal: String){
    ADICAO("+"),
    SUBTRACAO("-"),
    MULTIPLICAO("*"),
    DIVISAO("/"),
    EQUAL("=");

    override fun toString(): String {
        return this.sinal
    }
}

enum class Numeros (val numero: String){
    UM("1"),
    DOIS("2"),
    TRES("3"),
    QUATRO("4"),
    CINCO("5"),
    SEIS("6"),
    SETE("7"),
    OITO("8"),
    NOVE("9"),
    PONTO("."),
    ZERO("0"),
    APAGAR("<");

    override fun toString(): String {
        return this.numero
    }
}

@ExperimentalMaterialApi
@Composable
fun CalculatorCustom() {
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
            for (i in Sinais.values()) {
                TextButton(
                    onClick = {
                        numberAction(i.name)
                        if (setResult){
                        }
                    }, modifier = Modifier
                        .widthIn(min = 65.dp, max = 800.dp)
                ) {
                    Text(text = i.name)
                }
            }
        }
        CustomRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.Start),
            onClick = {}
        ) {
            for (i in Numeros.values()) {
                TextButton(
                    onClick = {
                        numberAction(i.name)
                    },
                    modifier = Modifier
                        .widthIn(min = 100.dp, max = 800.dp)
                ) {
                    Text(text = i.name)
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(text: String, onclick: () -> Unit) {
    Box(modifier = Modifier
        .padding(2.dp)
        .size(24.dp)
        .wrapContentSize()
        .clickable { onclick() }){
        Text(text = text, style = MaterialTheme.typography.button, fontWeight = FontWeight.Black)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Calculator(numero: String, onNumeroChange: (String) -> Unit, onclick: (String) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentHeight(align = Alignment.CenterVertically),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        DefaultInputText(text = numero, onTextChange = { onNumeroChange(it) })
        Divider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            for(s in Sinais.values()){
                CalculatorButton(text = s.sinal, onclick = { onclick(s.sinal) })
            }
        }
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ){
            items(Numeros.values()){
                CalculatorButton(text = it.numero, onclick = { onclick(it.numero) })
            }
        }
    }
}

@Preview
@Composable
fun PrevCalculator() {
    Calculator("numero", {}, {})
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun PrevCustomRowCalculator() {
    CalculatorCustom()
}