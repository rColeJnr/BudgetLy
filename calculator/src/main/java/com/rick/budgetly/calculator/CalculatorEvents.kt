package com.rick.budgetly.calculator

internal sealed class CalculatorEvents {
    data class CalculatorEVent( val symbol: String): CalculatorEvents()
}
