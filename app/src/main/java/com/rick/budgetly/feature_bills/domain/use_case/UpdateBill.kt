package com.rick.budgetly.feature_bills.domain.use_case

import com.rick.budgetly.feature_bills.domain.Bill
import com.rick.budgetly.feature_bills.domain.IBillRepository
import com.rick.budgetly.feature_bills.util.validateBill

class UpdateBill (
    private val repository: IBillRepository
){
    suspend operator fun invoke(bill: Bill){
        validateBill(bill = bill)
        repository.updateBill(bill)
    }
}