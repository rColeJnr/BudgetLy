package com.rick.budgetly.feature_account.domain.use_case

import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.rick.budgetly.feature_account.domain.IQuoteApi
import com.rick.budgetly.feature_account.domain.Quote
import com.rick.budgetly.feature_account.persistence.QuoteRepositoryImpl
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response
import java.lang.RuntimeException


class GetQuoteTest{

    private lateinit var repository: QuoteRepositoryImpl
    private val api: IQuoteApi = mock()
    private val quote: Response<Quote> = mock()

    @Test
    fun `get quote from api`(): Unit = runBlocking{
        mockSuccessfulCase()

        repository.getQuote()

        verify(api, atLeastOnce()).getQuote()
    }

    @Test
    fun `error when fail to get quote`() = runBlocking {
        mockErrorCase()

        assertEquals(RuntimeException(), repository.getQuote().exceptionOrNull()?.message)
    }

    private suspend fun mockErrorCase() {
        whenever(api.getQuote()).thenThrow(RuntimeException())

        repository = QuoteRepositoryImpl(api)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.getQuote()).thenReturn(quote)

        repository = QuoteRepositoryImpl(api)
    }

}