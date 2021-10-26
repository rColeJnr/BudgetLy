package com.rick.budgetly.feature_account.domain.use_case

import com.rick.budgetly.feature_account.domain.IAccountRepository
import com.rick.budgetly.feature_account.ui.accounts.AccountsContainer
import com.rick.budgetly.feature_account.ui.accounts.AccountsViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

//TODO Forward retrieved data to viewModel

class GetAccounts @Inject constructor(
    private val repository: IAccountRepository,
    private val container: AccountsContainer?,
    private val viewModel: AccountsViewModel
) {

    suspend operator fun invoke(){
        repository.getAccounts(
            { accounts ->
                println(accounts)
                viewModel.apply {
                    accounts.onEach {
                        _accountsState.value = accountsState.value.copy(
                            accounts = it
                        )
                    }
                }
            },
            { container?.showError("Failed to get accounts from database") }
        )
    }

}

class GetAccountById @Inject constructor(
    private val repository: IAccountRepository,
    private val container: AccountsContainer?,
    private val viewModel: AccountsViewModel
){
    suspend operator fun invoke(id: Int){
        repository.getAccountById(
            id,
            { account ->
                println(account)
                // TODO account details view model
            },
            {container?.showError("Invalid id")}
        )
    }
}

class GetAccountByType @Inject constructor(
    private val repository: IAccountRepository,
    private val container: AccountsContainer?,
    private val viewModel: AccountsViewModel
){
    suspend operator fun invoke(type: String) {
        repository.getAccountByType(
            type,
            { accounts ->
                viewModel.apply {
                    accounts.onEach {
                        _accountsState.value = accountsState.value.copy(
                            accountsByType = it
                        )
                    }
                }
            },
            {container?.showError("No account match the given type")}
        )
    }
}