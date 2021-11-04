package com.rick.budgetly.feature_account.common


interface BaseLogic<EVENT> {

    fun onEvent(event: EVENT)

}