package com.rick.data

import retrofit2.Response
import retrofit2.http.GET

interface IQuoteApi {

    @GET("/")
    suspend fun getQuote(): Response<Quote>
}

interface IQuoteRepository {
    suspend fun getQuote(): Result<Response<Quote>>
}