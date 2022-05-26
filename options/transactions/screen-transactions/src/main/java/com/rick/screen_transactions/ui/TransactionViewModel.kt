package com.rick.screen_transactions.ui

import androidx.lifecycle.ViewModel
import com.rick.data_transactions.domain.TransactionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transactionUseCases: com.rick.data_transactions.domain.TransactionUseCases
): ViewModel() {
}