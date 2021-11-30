package com.rick.budgetly.feature_account.domain.use_case

import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.IAccountRepository
import com.rick.budgetly.feature_account.ui.accounts.AccountsContainer
import com.rick.budgetly.feature_account.ui.accounts.AccountsViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//TODO Forward retrieved data to viewModel

class GetAccounts @Inject constructor(
    private val repository: IAccountRepository
) {

    operator fun invoke(): Flow<List<Account>> {
         return repository.getAccounts()
    }

}

class GetAccountById @Inject constructor(
    private val repository: IAccountRepository
){
    suspend operator fun invoke(id: Int): Account? {
        return repository.getAccountById(id)
    }
}

// This doesn't work, it's not well done, but i'll get to it when we write room db tests
class GetAccountByType @Inject constructor(
    private val repository: IAccountRepository
){
    suspend operator fun invoke(type: String): Flow<List<Account>> {
        return repository.getAccountByType(type)
    }
}