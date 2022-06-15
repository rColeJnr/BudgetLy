package com.rick.budgetly.feature_account.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.rick.budgetly.feature_account.persistence.AccountRepositoryImplTest
import com.rick.util.dummyAccounts
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AccountUseCaseTest {

    private lateinit var getAccounts: GetAccounts
    private lateinit var getAccountByType: GetAccountByType
    private lateinit var getAccountById: GetAccountById
    private lateinit var saveAccount: SaveAccount
    private lateinit var deleteAccount: DeleteAccount
    private lateinit var fakeRepository: AccountRepositoryImplTest

    @Before
    fun setup() {
        fakeRepository = AccountRepositoryImplTest()
        saveAccount = SaveAccount(fakeRepository)
        getAccounts = GetAccounts(fakeRepository)
        getAccountByType = GetAccountByType(fakeRepository)
        getAccountById = GetAccountById(fakeRepository)
        deleteAccount = DeleteAccount(fakeRepository)

        runBlocking { dummyAccounts.forEach { saveAccount(it) } }
    }

    @Test
    fun `get accounts`() = runBlocking {
        var accounts = getAccounts().first()

        assertThat(accounts.size).isGreaterThan(0)
    }

    // this is a room thing i will maybe found out how to do
    @Test
    fun `get account by type`() = runBlocking {
        val accounts = getAccounts().first()
        assertThat(accounts[0].type).isEqualTo("Baddie")
    }

    @Test
    fun `get account by id`() = runBlocking {
        val account = getAccountById(0)
        assertThat(account?.title).isEqualTo("Thick")
    }

    @Test
    fun `delete account`() = runBlocking {
        val account = getAccountById(0)!!
        deleteAccount(account)
        assertThat(getAccounts().first().contains(account)).isFalse()
    }
}