package com.rick.budgetly.feature_account.ui.accounts

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rick.budgetly.feature_account.common.BaseLogic
import com.rick.budgetly.feature_account.common.ProductionDispatcherProvider
import com.rick.budgetly.feature_account.domain.AccountType
import com.rick.budgetly.feature_account.domain.Quote
import com.rick.budgetly.feature_account.domain.use_case.AccountUseCases
import com.rick.budgetly.feature_account.domain.use_case.GetQuote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Response
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

    val response: MutableLiveData<Response<Quote>> = MutableLiveData()
    internal val quote = mutableStateOf("Random Kanye Quote")
    init {
        // Get accounts
        onStart()
        getQuote()
    }

    override fun onEvent(event: AccountEvents) {
        when (event) {
            AccountEvents.ToggleAccount -> onAccountToggled()
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
            try {
                response.value = getQuote.invoke()
            } catch (e: Exception){
                Log.d("quote", e.message!!)
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
    }

}

/*
* because as much as you may not likeTo admit, idid get quiter
* keyboard, and supposingly moch comforable
* than the previous one, i just odnt dont kf the size
* will come to be a problm wiith egonormics but as far as placing it in aconfortable place
* that wotnbe a probwm
* plus the keys are layout jsut like the notebook keys
* what is dope and more confortable to which between keyboard
* also it chicklet keys, what means that the typing experience
* is shit faced, but yeah, 1550, fuck me.
* */