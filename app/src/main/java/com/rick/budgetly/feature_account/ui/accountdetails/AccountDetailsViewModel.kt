package com.rick.budgetly.feature_account.ui.accountdetails

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rick.budgetly.feature_account.common.BaseLogic
import com.rick.budgetly.feature_account.common.ProductionDispatcherProvider
import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.use_case.AccountUseCases
import com.rick.budgetly.feature_account.ui.util.getMainAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.onEach
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
    
    internal var currentAccount: Account? = null
    private var deletedAccount: Account? = null

    private val accountBalance = mutableStateOf(currentAccount?.balance)
    private val accountInclude = mutableStateOf(currentAccount?.include)
    private val accountMain = mutableStateOf(currentAccount?.main)
    private lateinit var prevAccountMain: Account

    override fun onEvent(event: AccountDetailsEvents) {
        when (event) {
            is AccountDetailsEvents.ChangeBalance -> onBalanceChanged(event.balance)
            AccountDetailsEvents.ChangeIncludeInTotalStatus -> onIncludeInTotalStatusChanged()
            AccountDetailsEvents.ChangeMainStatus -> onMainStatusChanged()
            AccountDetailsEvents.RestoreAccount -> onRestoreAccount()
            AccountDetailsEvents.SaveChanges -> onAccountChanged()
            is AccountDetailsEvents.DeleteAccount -> onAccountDeleted(account = event.account)
        }
    }

    private fun onAccountChanged() = launch {
        currentAccount?.let {
            accountUseCases.saveAccount(
                it.copy(
                    balance = accountBalance.value!!,
                    include = accountInclude.value!!,
                    main = accountMain.value!!
                )
            )
            prevAccountMain.main = false
            accountUseCases.saveAccount(prevAccountMain)
        }
    }

    private fun onIncludeInTotalStatusChanged() {
        accountInclude.value = accountInclude.value!!.not()
    }

    private fun onMainStatusChanged() = launch{
        accountUseCases.getAccounts().onEach { accounts ->
            prevAccountMain = getMainAccount(accounts)
            currentAccount!!.main = true
        }
    }

    private fun onBalanceChanged(balance: String) {
        accountBalance.value = balance
    }

    private fun onRestoreAccount() {
        launch {
            accountUseCases.saveAccount(deletedAccount ?: return@launch)
            deletedAccount = null
        }
    }

    private fun onAccountDeleted(account: Account) {
        launch {
            deletedAccount = account
            accountUseCases.deleteAccount(account)
        }
    }
}