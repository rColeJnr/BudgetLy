package com.rick.budgetly.calculator

internal sealed class Events {
    data class CalculatorEVent( val symbol: String): Events()
}
