package com.rick.add_edit

import androidx.compose.ui.graphics.Color

sealed class AccountAddEditEvents {

    data class EnteredTitle(val accountTitle: String): AccountAddEditEvents()
    data class EnteredDescription(val accountDescription: String): AccountAddEditEvents()
    data class ChangeAccountIcon(val accountIcon: Int ): AccountAddEditEvents()
    data class ChangeAccountColor(val accountColor: Color ): AccountAddEditEvents()
    data class ChangeAccountType( val accountType: String ): AccountAddEditEvents()
    data class ChangeAccountCurrency( val accountCurrency: String): AccountAddEditEvents()
    data class EnteredAccountLimit(val accountLimit: String ): AccountAddEditEvents()
    data class EnteredAccountBalance( val accountBalance: String): AccountAddEditEvents()
    data class ChangeIncludeInTotalStatus( val include: Boolean): AccountAddEditEvents()
    data class CalculatorEvent( val symbol: String): AccountAddEditEvents()
    object SaveAccount: AccountAddEditEvents()
    object CancelAccount: AccountAddEditEvents()
    object LimitClick: AccountAddEditEvents()
    object BalanceClick: AccountAddEditEvents()
    object CurrencyClick: AccountAddEditEvents()
    object TypeClick: AccountAddEditEvents()

}