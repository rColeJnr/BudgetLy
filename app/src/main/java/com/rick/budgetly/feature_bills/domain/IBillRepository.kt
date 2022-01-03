package com.rick.budgetly.feature_bills.domain

import kotlinx.coroutines.flow.Flow

interface IBillRepository {

    suspend fun createBill(bill: Bill)

    suspend fun updateBill(bill: Bill)

    suspend fun removeBill(bill: Bill)

    fun getBills(): Flow<List<Bill>>

    fun getBill(billId: Int): Flow<Bill?>

}