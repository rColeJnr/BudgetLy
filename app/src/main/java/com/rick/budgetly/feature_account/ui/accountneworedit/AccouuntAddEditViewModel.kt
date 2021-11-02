package com.rick.budgetly.feature_account.ui.accountneworedit

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.rick.budgetly.feature_account.common.BaseLogic
import com.rick.budgetly.feature_account.common.ProductionDispatcherProvider
import com.rick.budgetly.feature_account.domain.*
import com.rick.budgetly.feature_account.domain.use_case.AccountUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class AccountAddEditViewModel @Inject constructor(
    private val accountUseCases: AccountUseCases,
    private val dispatcher: ProductionDispatcherProvider
): ViewModel(), BaseLogic<AccountAddEditEvents>, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher.provideIOContext()

    var currentAccount: Account? = null

    internal val accountColor = mutableStateOf(AccountColor.Default.color.toArgb())

    internal  val accountIcon = mutableStateOf(-1)

    internal  val accountType = mutableStateOf(AccountType.Default.type)

    internal  val accountCurrency = mutableStateOf(AccountCurrency.Default.currency)

    internal  val accountLimit = mutableStateOf("")

    internal  val accountBalance = mutableStateOf("")

    internal  val accountTitle= mutableStateOf("")

    internal  val accountDescription= mutableStateOf("")

    internal  val accountInTotalStatus = mutableStateOf(true)

    internal  val accountMain = mutableStateOf(false)

    internal fun onStart(account: Account?){
        account?.let {
            accountColor.value = it.color
            accountIcon.value = it.icon
            accountType.value = it.type
            accountCurrency.value = it.currency
            accountLimit.value = it.limit
            accountBalance.value = it.balance
            accountTitle.value = it.title
            accountInTotalStatus.value = it.include
            accountMain.value = it.main
        }
    }

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

    private fun onSaveAccount() = launch {
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
            main = accountMain.value
        )
        accountUseCases.saveAccount(currentAccount!!)
    }

    private fun onCancelAccount() {
        currentAccount = null
        // navigate up
    }
}