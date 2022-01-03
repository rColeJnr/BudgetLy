package com.rick.budgetly.feature_bills.domain.use_case

import com.rick.budgetly.feature_bills.domain.Bill
import com.rick.budgetly.feature_bills.domain.IBillRepository

class RemoveBill(
    private val repository: IBillRepository
){
    suspend operator fun invoke(bill: Bill){
        repository.removeBill(bill)
    }
}
