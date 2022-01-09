package com.rick.budgetly.feature_options.transactions.ui

import androidx.lifecycle.ViewModel
import com.rick.budgetly.feature_options.transactions.domain.TransactionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transactionUseCases: TransactionUseCases
): ViewModel() {
}