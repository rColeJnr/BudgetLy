package com.rick.budgetly.feature_account

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rick.budgetly.BudgetLyActivity
import com.rick.budgetly.BudgetLyNavHost
import com.rick.budgetly.feature_account.domain.AccountCurrency
import com.rick.budgetly.feature_account.domain.AccountType
import com.rick.budgetly.feature_account.ui.accounts.AccountsNavHost
import com.rick.budgetly.feature_account.ui.util.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AccountsEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<BudgetLyActivity>()
    lateinit var navController: NavHostController

    @Before
    fun setup() {
        hiltRule.inject()
        composeRule.setContent {
            navController = rememberNavController()
            BudgetLyNavHost(navController = navController)

        }
    }

    @Test
    fun createAccount_accountDetails_editAccount() {
        composeRule.onNodeWithContentDescription("Add new account")
            .assertIsDisplayed()
            .performClick()

        composeRule
            .onNodeWithTag(TestTags.newAccountTitle)
            .performTextInput("test-title")

        composeRule
            .onNodeWithText(AccountType.Default.type)
            .performClick()
        composeRule
            .onAllNodesWithContentDescription(TestTags.dropDownItem)
            .onLast()
            .performClick()

        composeRule
            .onNodeWithText(AccountCurrency.Default.currency)
            .performClick()
        composeRule
            .onAllNodesWithContentDescription(TestTags.dropDownItem)
            .onLast()
            .performClick()

        composeRule
            .onAllNodesWithText("IncludeInTotal")
            .onFirst()
            .performClick()

        composeRule
            .onNodeWithTag(TestTags.newAccountDescription)
            .performTextInput("test-description")


        composeRule
            .onNodeWithTag(TestTags.newAccountLimit)
            .performTextInput("1232424")


        composeRule
            .onNodeWithTag(TestTags.newAccountBalance)
            .performTextInput("3435")

        composeRule
            .onNodeWithContentDescription("SaveAccount")
            .performClick()

        composeRule.onNodeWithContentDescription("Add new account")
            .assertIsDisplayed()
            .performClick()

        composeRule
            .onNodeWithTag(TestTags.newAccountTitle)
            .performTextInput("test-title2")

        composeRule
            .onNodeWithText(AccountType.Default.type)
            .performClick()
        composeRule
            .onAllNodesWithContentDescription(TestTags.dropDownItem)
            .onFirst()
            .performClick()

        composeRule
            .onNodeWithText(AccountCurrency.Default.currency)
            .performClick()
        composeRule
            .onAllNodesWithContentDescription(TestTags.dropDownItem)
            .onFirst()
            .performClick()

        composeRule
            .onAllNodesWithText("IncludeInTotal")
            .onFirst()
            .assertExists()

        composeRule
            .onNodeWithTag(TestTags.newAccountDescription)
            .performTextInput("test-description2")


        composeRule
            .onNodeWithTag(TestTags.newAccountLimit)
            .performTextInput("1232424")


        composeRule
            .onNodeWithTag(TestTags.newAccountBalance)
            .performTextInput("34352")

        composeRule
            .onNodeWithContentDescription("SaveAccount")
            .performClick()

        composeRule
            .onNodeWithText("test-title").assertIsDisplayed()

        composeRule
            .onNodeWithText("$ 3,435").assertIsDisplayed()

        composeRule
            .onAllNodesWithContentDescription("AccountRow")
            .onFirst()
            .performClick()

        composeRule
            .onNodeWithContentDescription("Settings")
            .performClick()

        composeRule
            .onNodeWithContentDescription("CancelAccount")
            .performClick()

        composeRule
            .onNodeWithContentDescription(TestTags.detailsMoreVert)
            .performClick()
            .onChild()

        composeRule
            .onAllNodesWithContentDescription(TestTags.detailsMoreVertItem)
            .onLast()
            .performClick()

        composeRule
            .onNodeWithContentDescription("NavigateUp")
            .performClick()

        composeRule
            .onAllNodesWithContentDescription("AccountRow")
            .onFirst()
            .performClick()

        composeRule
            .onNodeWithContentDescription("NavigateUp")
            .performClick()
    }


}