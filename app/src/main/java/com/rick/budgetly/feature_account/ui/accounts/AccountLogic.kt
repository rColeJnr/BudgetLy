package com.rick.budgetly.feature_account.ui.accounts

import com.rick.budgetly.feature_account.common.BaseLogic
import com.rick.budgetly.feature_account.common.DispatcherProvider
import com.rick.budgetly.feature_account.common.ProductionDispatcherProvider
import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.use_case.AccountUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class AccountLogic @Inject constructor(
    private val viewModel: AccountsViewModel,
    private val dispatcher: ProductionDispatcherProvider,
    private val accountUseCases: AccountUseCases
): BaseLogic<AccountEvents>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher.provideIOContext() + jobTracker

    init {
        jobTracker = Job()
    }

    // The logical fix is to move the events to viewModel
    // and leave the functions here, thus, the logic here,
    override fun onEvent(event: AccountEvents) {
        when(event){
            is AccountEvents.DeleteAccount -> onAccountDeleted(event.account)
            AccountEvents.RestoreAccount -> onRestoreAccount()
            AccountEvents.ToggleAccount -> onAccountToggled()
            is AccountEvents.ToggleAccountType -> onAccountTypeToggled(event.accountType)
            AccountEvents.OnStart -> onStart()
            AccountEvents.OnStop -> onStop()
        }
    }

    private fun onStart() = launch {
        accountUseCases.getAccounts().onEach { accounts ->
            viewModel.apply {
                _accountsState.value = accountsState.value.copy(
                    accounts = accounts
                )
            }
        }
    }

    // Congratulashings
    private fun onAccountTypeToggled(accountType: AccountType) = launch {

        when(accountType){

            AccountType.DEBTS -> {
                accountUseCases.getAccountsByType(accountType.type).onEach { accounts ->
                    viewModel.apply {
                        _accountsState.value = accountsState.value.copy(
                            accountsByTypeDebts = accounts
                        )
                        accountsState.value.isAccountTypeVisible[accountType.type]?.not()
                        _accountsState.value = accountsState.value.copy(
                            isAccountTypeVisible = accountsState.value.isAccountTypeVisible
                        )
                    }
                }
            }
            AccountType.LOANS -> {
                accountUseCases.getAccountsByType(accountType.type).onEach { accounts ->
                    viewModel.apply {
                        _accountsState.value = accountsState.value.copy(
                            accountsByTypeLoans = accounts
                        )
                        accountsState.value.isAccountTypeVisible[accountType.type]?.not()
                        _accountsState.value = accountsState.value.copy(
                            isAccountTypeVisible = accountsState.value.isAccountTypeVisible
                        )
                    }
                }
            }
            AccountType.SAVINGS -> {
                accountUseCases.getAccountsByType(accountType.type).onEach { accounts ->
                    viewModel.apply {
                        _accountsState.value = accountsState.value.copy(
                            accountsByTypeSavings = accounts
                        )
                        accountsState.value.isAccountTypeVisible[accountType.type]?.not()
                        _accountsState.value = accountsState.value.copy(
                            isAccountTypeVisible = accountsState.value.isAccountTypeVisible
                        )
                    }
                }
            }
        }

    }

    private fun onAccountToggled() {
        TODO ("navigate to account options")
    }

    private fun onRestoreAccount() {
        launch {
            accountUseCases.saveAccount(viewModel.lastDeletedAccount ?: return@launch)
            viewModel.lastDeletedAccount = null
        }
    }

    private fun onAccountDeleted(account: Account) {
        launch {
            accountUseCases.deleteAccount(account)
            viewModel.lastDeletedAccount = account
        }
    }

    private fun onStop() {
        TODO("dont remember what i'm supposed to do here")
    }

}

class ProvideAccountLogic() {
    @Inject
    lateinit var accountLogic: AccountLogic
}