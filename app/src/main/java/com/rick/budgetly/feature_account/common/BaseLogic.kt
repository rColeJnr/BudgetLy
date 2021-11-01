package com.rick.budgetly.feature_account.common

import kotlinx.coroutines.Job

interface BaseLogic<EVENT> {

    fun onEvent(event: EVENT)

}