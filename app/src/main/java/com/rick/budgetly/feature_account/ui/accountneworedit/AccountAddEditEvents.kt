package com.rick.budgetly.feature_account.ui.accountneworedit

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AccountAddEditEvents {

    data class EnteredTitle(val accountTitle: String): AccountAddEditEvents()
    data class ChangeAccountIcon(val accountIcon: ImageVector ): AccountAddEditEvents()
    data class ChangeAccountColor(val accountColor: Color ): AccountAddEditEvents()
    data class ChangeAccountType( val accountType: String ): AccountAddEditEvents()
    data class ChangeAccountCurrency( val accountCurrency: String): AccountAddEditEvents()
    data class EnteredCreditLimit( val accountLimit: String ): AccountAddEditEvents()
    data class EnteredAccountBalance( val accountBalance: String): AccountAddEditEvents()
    data class ChangeIncludeInTotalStatus( val include: Boolean): AccountAddEditEvents()
    object SaveAccount: AccountAddEditEvents()
    object CancelAccount: AccountAddEditEvents()

}