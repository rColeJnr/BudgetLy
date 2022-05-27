package com.rick.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rick.budgetly.feature_account.common.BaseLogic
import com.rick.budgetly.feature_account.common.ProductionDispatcherProvider
import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.AccountIcon
import com.rick.budgetly.feature_account.domain.AccountType
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
    private val dispatcher: ProductionDispatcherProvider,
    savedStateHandle: SavedStateHandle
) : ViewModel(), BaseLogic<AccountDetailsEvents>, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher.provideIOContext()

    internal var currentAccount: Account? = null
    private var deletedAccount: Account? = null
    private var prevAccountMain: Account? = null
    internal val accountInclude = mutableStateOf(true)
    internal var accountBalance = mutableStateOf("0")
    internal val accountTitle = mutableStateOf("")
    internal val accountIcon = mutableStateOf(AccountIcon.Position)
    private val accountMain = mutableStateOf(false)
    internal var accountId: Int? = null
    internal var accountType = AccountType.Default.type

    init {
        viewModelScope.launch{
            val job = viewModelScope.launch(dispatcher.provideIOContext()) {
                savedStateHandle.get<Int>("account")?.let {
                    currentAccount = accountUseCases.getAccountById(it)!!
                }
            }
            job.join()
            currentAccount!!.let { account ->
                accountInclude.value = account.include
                accountBalance.value = account.balance
                accountTitle.value = account.title
                accountIcon.value = account.icon
                accountMain.value = account.main
                accountType = account.type
                accountId = account.id
            }
        }
    }

    override fun onEvent(event: AccountDetailsEvents) {
        when (event) {
            AccountDetailsEvents.ChangeIncludeInTotalStatus -> onIncludeInTotalStatusChanged()
            AccountDetailsEvents.ChangeMainStatus -> onMainStatusChanged()
            AccountDetailsEvents.RestoreAccount -> onRestoreAccount()
            is AccountDetailsEvents.DeleteAccount -> onAccountDeleted(account = event.account)
        }
    }

    private fun onAccountChanged() = launch {
        currentAccount?.let {
            accountUseCases.saveAccount(
                it.copy(
                    include = accountInclude.value,
                    main = accountMain.value
                )
            )
        }

        prevAccountMain?.let{
            accountUseCases.saveAccount(it)
        }
    }

    private fun onIncludeInTotalStatusChanged() {
        accountInclude.value = accountInclude.value.not()
        onAccountChanged()
    }

    private fun onMainStatusChanged() = launch{
        accountUseCases.getAccounts().onEach { accounts ->
            prevAccountMain = getMainAccount(accounts)
            prevAccountMain?.main = false
            accountMain.value = true
        }
        onAccountChanged()
    }

    private fun onRestoreAccount() {
        // should share account with accounts viewmodel
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