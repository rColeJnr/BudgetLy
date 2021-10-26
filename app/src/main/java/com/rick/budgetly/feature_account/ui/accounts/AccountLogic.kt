package com.rick.budgetly.feature_account.ui.accounts

import com.rick.budgetly.feature_account.common.BaseLogic
import com.rick.budgetly.feature_account.common.DispatcherProvider
import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.use_case.AccountUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class AccountLogic @Inject constructor(
    private val viewModel: AccountsViewModel,
    private val accountUseCases: AccountUseCases,
    private val dispatcher: DispatcherProvider
): BaseLogic<AccountEvents>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher.provideIOContext() + jobTracker

    init {
        jobTracker = Job()
    }

    override fun onEvent(event: AccountEvents) {
        when(event){
            is AccountEvents.DeleteAccount -> onAccountDeleted(event.account)
            AccountEvents.RestoreAccount -> onRestoreAccount()
            AccountEvents.ToggleAccount -> onAccountToggled()
            is AccountEvents.ToggleAccountType -> onAccountTypeToggled(event.type)
            AccountEvents.OnStart -> onStart()
            AccountEvents.OnStop -> onStop()
        }
    }

    private fun onStart() = launch {
        accountUseCases.getAccounts()
    }

    private fun onAccountTypeToggled(type: String) = launch {

    }

    private fun onAccountToggled() {

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
        TODO("Not yet implemented")
    }

}