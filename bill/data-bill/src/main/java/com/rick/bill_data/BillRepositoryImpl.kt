package com.rick.bill_data

import com.rick.bill_data.domain.Bill
import com.rick.bill_data.domain.IBillRepository

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