package com.rick.budgetly.feature_account.ui.accounts

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rick.budgetly.BudgetLyActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AccountBodyTest {

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
            AccountsNavHost()
        }
    }

    @Test
    fun checkIfAccountsBody_IsDisplayed() {
        composeRule.onNodeWithContentDescription("Accounts Screen")
            .assertIsDisplayed()
    }

}