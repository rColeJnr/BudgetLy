package com.rick.budgetly.feature_account.ui.accounts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rick.budgetly.feature_account.common.BaseLogic
import com.rick.budgetly.feature_account.common.ProductionDispatcherProvider
import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.use_case.AccountUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val accountUseCases: AccountUseCases,
    private val dispatcher: ProductionDispatcherProvider,
) : ViewModel(), BaseLogic<AccountEvents>, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher.provideIOContext()

    private val _accountsState = mutableStateOf(AccountsState())
    internal val accountsState: State<AccountsState> = _accountsState

    private var lastDeletedAccount: Account? = null

    override fun onEvent(event: AccountEvents) {
        when (event) {
            is AccountEvents.DeleteAccount -> onAccountDeleted(
                accountUseCases = accountUseCases,
                account = event.account
            )
            AccountEvents.RestoreAccount -> onRestoreAccount(accountUseCases)
            AccountEvents.ToggleAccount -> onAccountToggled()
            is AccountEvents.ToggleAccountType -> onAccountTypeToggled(
                accountUseCases = accountUseCases,
                event.accountType
            )
            AccountEvents.OnStart -> onStart(accountUseCases)
            AccountEvents.OnStop -> onStop(accountUseCases)
        }
    }


    private fun onStart(accountUseCases: AccountUseCases) = launch {
        accountUseCases.getAccounts().onEach { accounts ->

            _accountsState.value = accountsState.value.copy(
                accounts = accounts
            )

        }
    }

    // Congratulashings
    private fun onAccountTypeToggled(accountUseCases: AccountUseCases, accountType: AccountType) =
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

    private fun onRestoreAccount(accountUseCases: AccountUseCases) {
        launch {
            accountUseCases.saveAccount(lastDeletedAccount ?: return@launch)
            lastDeletedAccount = null
        }
    }

    private fun onAccountDeleted(accountUseCases: AccountUseCases, account: Account) {
        launch {
            accountUseCases.deleteAccount(account)
            lastDeletedAccount = account
        }
    }

    private fun onStop(accountUseCases: AccountUseCases) {
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