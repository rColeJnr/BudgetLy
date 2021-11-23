package com.rick.budgetly.feature_account.persistence

import com.rick.budgetly.feature_account.domain.IQuoteApi
import com.rick.budgetly.feature_account.domain.IQuoteRepository
import com.rick.budgetly.feature_account.domain.Quote
import retrofit2.Response
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val api: IQuoteApi
): IQuoteRepository{
    override suspend fun getQuote(): Response<Quote> {
        return api.getQuote()
    }
}