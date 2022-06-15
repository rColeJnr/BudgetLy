package com.rick.bill_data.domain.use_case

data class BillUseCases(
    val getBills: GetBills,
    val getBill: GetBill,
    val createBill: CreateBill,
    val updateBill: UpdateBill,
    val removeBill: RemoveBill,
)