package com.rick.core

sealed class BudgetLyContainer {

    data class ShowError(val message: String): BudgetLyContainer()
    data class ShowRestoreSnackbar(val message: String): BudgetLyContainer()
    object ShowSuccess: BudgetLyContainer()

}