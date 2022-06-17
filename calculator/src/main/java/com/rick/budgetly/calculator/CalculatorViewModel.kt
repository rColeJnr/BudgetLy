package com.rick.budgetly.calculator

import androidx.lifecycle.ViewModel
import com.rick.core.BaseLogic

internal class CalculatorViewModel() : ViewModel(), BaseLogic<CalculatorEvents> {

    override fun onEvent(event: CalculatorEvents) {
        when (event) {
            is CalculatorEvents.CalculatorEVent -> calculatorEvent(event.symbol)
        }
    }

    private fun calculatorEvent(symbol: String) {
        numberAction(symbol)
    }
}