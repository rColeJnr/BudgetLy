package com.rick.bill_data.domain.use_case

import com.rick.bill_data.domain.Bill
import com.rick.bill_data.domain.IBillRepository
import com.rick.budgetly.feature_bills.util.validateBill

class CreateBill(
    private val repository: IBillRepository
){
    suspend operator fun invoke(bill: Bill){
        validateBill(bill)

        repository.createBill(bill = bill)
    }
}