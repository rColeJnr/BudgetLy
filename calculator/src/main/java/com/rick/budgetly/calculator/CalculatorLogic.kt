package com.rick.budgetly.calculator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

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

// We do it like apple calculator
// clean the screen after sub, or add, or else

private var canAddOperation = false
private var canChangeOperation = false
private var canAddDecimal = true
private var setResult = false
private var num1: String? = null
private var num2: String? = null
private var numero: MutableState<String> = mutableStateOf("")
private var operator: String = ""

private enum class Operators (val sinal: String){
    ADICAO("+"),
    SUBTRACAO("-"),
    MULTIPLICAO("*"),
    DIVISAO("/");

    override fun toString(): String {
        return this.sinal
    }
}

fun numberAction(symbol: String){

    if(symbol == "."){
        if (canAddDecimal) {
            numero.value += symbol
            canAddDecimal = false
        }
    }

    for (op in Operators.values()) {
        if (symbol == op.sinal){
            operationAction(symbol)
            canChangeOperation(symbol)
            break
        }
    }
    if (symbol == "<"){
        backSpace()
    }
    if (symbol == "="){
        if (operator.isNotEmpty()) num2 = numero.value
        else num1 = numero.value
        if (canCalculate(num1, num2)) {
            numero.value = calculate(num1 = num1!!, num2 = num2)
            operator = ""
            setResult = true
        }

    }

    if (symbol != "<" && symbol != ".") {
        for (num in Numeros.values()){
            if (symbol == num.numero) {
                numero.value += symbol
                canAddOperation = true
                canChangeOperation = false
                break
            }
        }
    }
}

private fun operationAction(operation: String){
    if (canAddOperation){
        num1 = numero.value
        operator = operation
        numero.value = ""
        canAddOperation = false
        canChangeOperation = true
        canAddDecimal = true
    }
}

private fun canChangeOperation(newOperator: String){
    if (canChangeOperation && numero.value.isEmpty()) operator = newOperator
}