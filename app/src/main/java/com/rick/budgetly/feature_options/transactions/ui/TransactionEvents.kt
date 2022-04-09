package com.rick.budgetly.feature_options.transactions.ui

import com.rick.budgetly.feature_options.transactions.domain.Transaction

sealed class TransactionEvents {

    object ToggleEditMode: TransactionEvents()
    data class Delete(val transaction: Transaction): TransactionEvents()

}