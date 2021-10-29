package com.rick.budgetly.feature_account.ui.accounts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rick.budgetly.feature_account.common.DispatcherProvider
import com.rick.budgetly.feature_account.domain.Account
import com.rick.budgetly.feature_account.domain.use_case.AccountUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
): ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcherProvider.provideIOContext()

    internal val _accountsState = mutableStateOf(AccountsState())
    internal val accountsState: State<AccountsState> = _accountsState

    internal var lastDeletedAccount: Account? = null

}