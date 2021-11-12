package com.rick.budgetly.components

/*
* lets sleep early, wake up early and go get us that ipad
* */

fun clearAll() = ""

fun backSpace(num: String) {
    val length = num.length
    if (length > 0) num.subSequence(0, length - 1)
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

fun canCalculate(num1: String, num2: String ): Boolean {
    if (num2.isNotEmpty()){
        if (num1.isNotEmpty()){
            return true
        }
    }
    if (num1.isNotEmpty() && num2.isEmpty()) return true
    return (num1.isNotEmpty() && num2.isNotEmpty())
}

fun calculate(num1: String, num2: String, operator: Operators): String {
    if (canCalculate(num1, num2)){
        return when (operator) {
            Operators.MULTIPLY -> multiply(num1, num2)
            Operators.DIVIDE -> divide(num1, num2)
            Operators.ADD -> add(num1, num2)
            Operators.SUBTRACT -> subtract(num1, num2)
        }
    }
    return ""
}

private fun adicionar(num1: Float, num2: Float) = num1 + num2
private fun subtrair(num1: Float, num2: Float) = num1 - num2
private fun multiplicar(num1: Float, num2: Float) = num1 * num2
private fun dividir(num1: Float, num2: Float) = num1 / num2

enum class Operators(val operator: String){
    MULTIPLY("*"),
    DIVIDE("/"),
    ADD("+"),
    SUBTRACT("-")
}

// We do it like apple calculator
// clean the screen after sub, or add, or else