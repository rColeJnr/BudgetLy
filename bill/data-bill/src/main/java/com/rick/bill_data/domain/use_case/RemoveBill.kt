package com.rick.bill_data.domain.use_case

import com.rick.bill_data.domain.Bill
import com.rick.bill_data.domain.IBillRepository

class RemoveBill(
    private val repository: IBillRepository
){
    suspend operator fun invoke(bill: Bill){
        repository.removeBill(bill)
    }
}
