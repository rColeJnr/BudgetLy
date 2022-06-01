package com.rick.data.use_case

import com.rick.data.IQuoteRepository
import com.rick.data.Quote
import retrofit2.Response
import javax.inject.Inject

class GetQuote @Inject constructor(
    private val repository: IQuoteRepository
) {

    suspend operator fun invoke(): Response<Quote> {
        return repository.getQuote().getOrNull()!!
    }

}