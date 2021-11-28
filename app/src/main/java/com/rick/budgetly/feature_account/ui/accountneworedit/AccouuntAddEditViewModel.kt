package com.rick.budgetly.feature_account.ui.accountneworedit

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rick.budgetly.feature_account.common.BaseLogic
import com.rick.budgetly.feature_account.common.ProductionDispatcherProvider
import com.rick.budgetly.feature_account.domain.*
import com.rick.budgetly.feature_account.domain.use_case.AccountUseCases
import com.rick.budgetly.feature_account.ui.accounts.AccountsContainer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountAddEditViewModel @Inject constructor(
    private val accountUseCases: AccountUseCases,
    private val dispatcher: ProductionDispatcherProvider,
    savedStateHandle: SavedStateHandle
): ViewModel(), BaseLogic<AccountAddEditEvents> {

    var currentAccount: Account? = null

    internal val accountColor = mutableStateOf(AccountColor.Position)

    internal  val accountIcon = mutableStateOf(AccountIcon.Position)

    internal  val accountType = mutableStateOf(AccountType.Default.type)

    internal  val accountCurrency = mutableStateOf(AccountCurrency.Default.currency)

    internal val accountLimit = mutableStateOf("")

    internal  val accountBalance = mutableStateOf("")

    internal  val accountTitle= mutableStateOf("")

    internal  val accountDescription= mutableStateOf("")

    internal  val accountInTotalStatus = mutableStateOf(true)

    internal var accountId: Int? = null

    private  val accountMain = mutableStateOf(false)

    private val _eventFlow = MutableSharedFlow<AccountsContainer>()
    val eventFlow = _eventFlow.asSharedFlow()

    override fun onEvent(event: AccountAddEditEvents) {
        when (event){
            AccountAddEditEvents.CancelAccount -> onCancelAccount()
            is AccountAddEditEvents.ChangeAccountColor -> onColorChange(event.accountColor)
            is AccountAddEditEvents.ChangeAccountCurrency -> onCurrencyChange(event.accountCurrency)
            is AccountAddEditEvents.ChangeAccountIcon -> onIconChange(event.accountIcon)
            is AccountAddEditEvents.ChangeAccountType -> onTypeChange(event.accountType)
            is AccountAddEditEvents.ChangeIncludeInTotalStatus -> onIncludeInTotalStatusChange(event.include)
            is AccountAddEditEvents.EnteredAccountBalance -> onBalanceEntered(event.accountBalance)
            is AccountAddEditEvents.EnteredCreditLimit -> onLimitEntered(event.accountLimit)
            is AccountAddEditEvents.EnteredTitle -> onTitleEntered(event.accountTitle)
            is AccountAddEditEvents.EnteredDescription -> onDescriptionEntered(event.accountDescription)
            AccountAddEditEvents.SaveAccount -> onSaveAccount()
        }
    }

    init{
        viewModelScope.launch{
            val job = viewModelScope.launch(dispatcher.provideIOContext()) {
                savedStateHandle.get<Int>("accountToEdit")?.let { it ->
                    if (it != -1){
                        currentAccount = accountUseCases.getAccountById(it)
                    }
                }
            }
            job.join()
            currentAccount?.let { account ->
                currentAccount = account
                accountColor.value = account.color
                accountIcon.value = account.icon
                accountType.value = account.type
                accountCurrency.value = account.currency
                accountLimit.value = account.limit
                accountBalance.value = account.balance
                accountTitle.value = account.title
                accountDescription.value = account.description
                accountInTotalStatus.value = account.include
                accountMain.value = account.main
                accountId = account.id
            }
        }
    }

    private fun onTitleEntered(title: String) {
        accountTitle.value = title
    }

    private fun onDescriptionEntered(description: String){
        accountDescription.value = description
    }

    private fun onLimitEntered(limit: String) {
        accountLimit.value = limit
    }

    private fun onBalanceEntered(balance: String) {
        accountBalance.value = balance
    }

    private fun onIncludeInTotalStatusChange(include: Boolean) {
        accountInTotalStatus.value = include
    }

    private fun onCurrencyChange(currency: String) {
        accountCurrency.value = currency
    }

    private fun onIconChange(icon: Int) {
        accountIcon.value = icon
    }

    private fun onTypeChange(type: String) {
        accountType.value = type
    }

    private fun onColorChange(color: Color) {
        accountColor.value = color.toArgb()
    }

    private fun onSaveAccount() = viewModelScope.launch {
        currentAccount = Account(
            title = accountTitle.value,
            type = accountType.value,
            currency = accountCurrency.value,
            balance = accountBalance.value,
            limit = accountLimit.value,
            description = accountDescription.value,
            icon = accountIcon.value,
            color = accountColor.value,
            include = accountInTotalStatus.value,
            main = accountMain.value,
            id = currentAccount?.id
        )
        try{
            accountUseCases.saveAccount(currentAccount!!)
            _eventFlow.emit(AccountsContainer.ShowSuccess)
        } catch (e: InvalidAccountException){
            _eventFlow.emit(
                AccountsContainer.ShowError(message = e.message ?: "Couldn't save note")
            )
        }
        currentAccount = null
    }

    private fun onCancelAccount() {
        currentAccount = null
    }

}