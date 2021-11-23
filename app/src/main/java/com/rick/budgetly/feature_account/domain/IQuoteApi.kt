package com.rick.budgetly.feature_account.domain

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface IQuoteApi {

    @GET("/")
    suspend fun getQuote(): Response<Quote>
}

interface IQuoteRepository {
    suspend fun getQuote(): Response<Quote>
}