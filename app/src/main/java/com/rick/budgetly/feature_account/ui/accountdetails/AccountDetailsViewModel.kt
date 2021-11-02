package com.rick.budgetly.feature_account.ui.accountdetails

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rick.budgetly.feature_account.common.BaseLogic
import com.rick.budgetly.feature_account.common.ProductionDispatcherProvider
import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.use_case.AccountUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class AccountDetailsViewModel @Inject constructor(
    private val accountUseCases: AccountUseCases,
    private val dispatcher: ProductionDispatcherProvider
) : ViewModel(), BaseLogic<AccountDetailsEvents>, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher.provideIOContext()
    
    private var currentAccount: Account? = null

    private val accountBalance = mutableStateOf(currentAccount?.balance)
    private val accountInclude = mutableStateOf(currentAccount?.include)

    override fun onEvent(event: AccountDetailsEvents) {
        when (event) {
            is AccountDetailsEvents.ChangeBalance -> onBalanceChanged(event.balance)
            is AccountDetailsEvents.ChangeIncludeInTotalStatus -> onIncludeInTotalStatusChanged(event.include)
            is AccountDetailsEvents.DeleteAccount -> onAccountDeleted(account = event.account)
            AccountDetailsEvents.RestoreAccount -> onRestoreAccount()
            AccountDetailsEvents.SaveChanges -> onAccountChanged()
        }
    }

    private fun onAccountChanged() = launch {
        currentAccount?.let {
            accountUseCases.saveAccount(
                it.copy(
                    balance = accountBalance.value!!,
                    include = accountInclude.value!!
                )
            )
        }
    }

    private fun onIncludeInTotalStatusChanged(include: Boolean) {
        accountInclude.value = include
    }

    private fun onBalanceChanged(balance: String) {
        accountBalance.value = balance
    }

    private fun onRestoreAccount() {
        launch {
            accountUseCases.saveAccount(currentAccount ?: return@launch)
            currentAccount = null
        }
    }

    private fun onAccountDeleted(account: Account) {
        launch {
            accountUseCases.deleteAccount(account)
        }
    }
}