package com.rick.budgetly.feature_account.common

import kotlin.coroutines.CoroutineContext

interface DispatcherProvider {

    fun provideUIContext(): CoroutineContext
    fun provideIOContext(): CoroutineContext

}