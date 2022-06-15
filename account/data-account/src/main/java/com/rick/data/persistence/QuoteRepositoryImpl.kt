package com.rick.data.persistence

import com.rick.data.IQuoteApi
import com.rick.data.IQuoteRepository
import com.rick.data.Quote
import retrofit2.Response
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val api: IQuoteApi
): IQuoteRepository {
    override suspend fun getQuote(): Result<Response<Quote>> {
        val result = api.getQuote()
        return if (result.isSuccessful)  Result.success(result)
        else Result.failure(RuntimeException("Something went wrong"))
    }
}