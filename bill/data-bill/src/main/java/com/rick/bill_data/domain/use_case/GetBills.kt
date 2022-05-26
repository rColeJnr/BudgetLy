package com.rick.bill_data.domain.use_case

import com.rick.bill_data.domain.Bill
import com.rick.bill_data.domain.IBillRepository
import kotlinx.coroutines.flow.Flow

class GetBills(
    private val repository: IBillRepository
){
    operator fun invoke(): Flow<List<Bill>> =
        repository.getBills()
}