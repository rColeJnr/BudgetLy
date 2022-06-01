package com.rick.accounts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rick.common.ProductionDispatcherProvider
import com.rick.core.BaseLogic
import com.rick.data.AccountType
import com.rick.data.use_case.AccountUseCases
import com.rick.data.use_case.GetQuote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val accountUseCases: AccountUseCases,
    private val getQuote: GetQuote,
    private val dispatcher: ProductionDispatcherProvider,
) : ViewModel(), BaseLogic<AccountEvents>, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher.provideIOContext()

    private var getAccountsJob: Job? = null

    private val _accountsState = mutableStateOf(AccountsState())
    internal val accountsState: State<AccountsState> = _accountsState

    init {
        // Get accounts
        onStart()
        getQuote()
    }

    override fun onEvent(event: AccountEvents) {
        when (event) {
            AccountEvents.ToggleAccount -> onAccountToggled()
            // TODO implement account type feature
            is AccountEvents.ToggleAccountType -> onAccountTypeToggled(event.accountType)
            AccountEvents.OnStart -> onStart()
            AccountEvents.OnStop -> onStop()
        }
    }


    private fun onStart() {
        getAccountsJob?.cancel()
        getAccountsJob = accountUseCases.getAccounts().onEach { accounts ->
            _accountsState.value = accountsState.value.copy(
                accounts = accounts
            )
        }
            .launchIn(viewModelScope)
    }

    private fun getQuote() {
        viewModelScope.launch {
            if (_accountsState.value.quote.isBlank()){
                try {
                    val response = getQuote.invoke().body()
                    // TODO: Move this to the viewModel, and only load it once a day.
                    // Api response
                    _accountsState.value = accountsState.value.copy(
                        quote = response!!.quote
                    )
                } catch (e: Exception) {
                    // TODO: Reload functionality.
                    // TODO: Show toast
                }
            }
        }
    }

    // Congratulashings
    private fun onAccountTypeToggled(accountType: AccountType) =
        launch {
            when (accountType) {
                AccountType.DEBTS -> {
                    accountUseCases.getAccountsByType(accountType.type).onEach { accounts ->
                        _accountsState.value = accountsState.value.copy(
                            accountsByTypeDebts = accounts
                        )
                        accountsState.value.isAccountTypeVisible[accountType.type]?.not()
                        _accountsState.value = accountsState.value.copy(
                            isAccountTypeVisible = accountsState.value.isAccountTypeVisible
                        )
                    }
                }
                AccountType.LOANS -> {
                    accountUseCases.getAccountsByType(accountType.type).onEach { accounts ->

                        _accountsState.value = accountsState.value.copy(
                            accountsByTypeLoans = accounts
                        )
                        accountsState.value.isAccountTypeVisible[accountType.type]?.not()
                        _accountsState.value = accountsState.value.copy(
                            isAccountTypeVisible = accountsState.value.isAccountTypeVisible
                        )

                    }
                }
                AccountType.SAVINGS -> {
                    accountUseCases.getAccountsByType(accountType.type).onEach { accounts ->

                        _accountsState.value = accountsState.value.copy(
                            accountsByTypeSavings = accounts
                        )
                        accountsState.value.isAccountTypeVisible[accountType.type]?.not()
                        _accountsState.value = accountsState.value.copy(
                            isAccountTypeVisible = accountsState.value.isAccountTypeVisible
                        )

                    }
                }
                AccountType.CASH -> {
                    accountUseCases.getAccountsByType(accountType.type).onEach { accounts ->

                        _accountsState.value = accountsState.value.copy(
                            accountsByTypeCash = accounts
                        )
                        accountsState.value.isAccountTypeVisible[accountType.type]?.not()
                        _accountsState.value = accountsState.value.copy(
                            isAccountTypeVisible = accountsState.value.isAccountTypeVisible
                        )

                    }
                }
            }
        }

    private fun onAccountToggled() {
        TODO("navigate to account options")
    }

    private fun onStop() {
        TODO("dont remember what i'm supposed to do here")
        // for starters you could make sure that the quote is saved
    }

}