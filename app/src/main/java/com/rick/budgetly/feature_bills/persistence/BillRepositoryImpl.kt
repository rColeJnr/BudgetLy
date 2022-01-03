package com.rick.budgetly.feature_bills.persistence

import com.rick.budgetly.feature_bills.domain.Bill
import com.rick.budgetly.feature_bills.domain.IBillRepository
import javax.inject.Inject
import javax.inject.Singleton

class BillRepositoryImpl (
    private val billDao: BillDao
): IBillRepository{

    override fun getBills() = billDao.getBills()

    override fun getBill(billId: Int) = billDao.getBillById(billId)

    override suspend fun createBill(bill: Bill){
        billDao.insert(bill)
    }

    override suspend fun updateBill(bill: Bill){
        billDao.update(bill)
    }

    override suspend fun removeBill(bill: Bill){
        billDao.delete(bill)
    }

}