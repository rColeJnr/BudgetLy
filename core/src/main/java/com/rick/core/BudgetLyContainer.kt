package com.rick.core

sealed class BudgetLyContainer {

    data class ShowError(val message: String): BudgetLyContainer()
    object ShowRestoreSnackbar: BudgetLyContainer()
    object ShowSuccess: BudgetLyContainer()

}