package com.rick.budgetly.feature_account.domain.use_case

import com.rick.budgetly.feature_account.domain.IQuoteRepository
import com.rick.budgetly.feature_account.domain.Quote
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class GetQuote @Inject constructor(
    private val repository: IQuoteRepository
) {

    suspend operator fun invoke(): Response<Quote> {
       return repository.getQuote().getOrNull()!!
    }

}