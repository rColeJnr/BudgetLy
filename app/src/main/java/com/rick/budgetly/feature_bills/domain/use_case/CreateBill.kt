package com.rick.budgetly.feature_bills.domain.use_case

import com.rick.budgetly.feature_bills.domain.Bill
import com.rick.budgetly.feature_bills.domain.IBillRepository
import com.rick.budgetly.feature_bills.domain.InvalidBillException
import com.rick.budgetly.feature_bills.util.validateBill

class CreateBill(
    private val repository: IBillRepository
){
    suspend operator fun invoke(bill: Bill){
        validateBill(bill)

        repository.createBill(bill = bill)
    }
}