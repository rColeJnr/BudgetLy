package com.rick.budgetly.feature_bills.domain.use_case

import com.rick.budgetly.feature_bills.domain.Bill
import com.rick.budgetly.feature_bills.domain.IBillRepository
import kotlinx.coroutines.flow.Flow

class GetBill(
    private val repository: IBillRepository
){
    operator fun invoke(billId: Int): Flow<Bill?> =
        repository.getBill(billId)
}