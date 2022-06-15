package com.rick.screen_transactions.ui

import com.rick.data_transactions.domain.Transaction

sealed class TransactionEvents {

    object ToggleEditMode: TransactionEvents()
    data class Delete(val transaction: com.rick.data_transactions.domain.Transaction): TransactionEvents()

}