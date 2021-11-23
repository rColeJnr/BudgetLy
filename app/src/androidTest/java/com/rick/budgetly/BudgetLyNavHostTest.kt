package com.rick.budgetly

import androidx.activity.viewModels
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rick.budgetly.BudgetLyActivity
import com.rick.budgetly.BudgetLyNavHost
import com.rick.budgetly.BudgetLyScreen
import com.rick.budgetly.feature_account.common.AccountsScreen
import com.rick.budgetly.ui.theme.BudgetLyTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BudgetLyNavHostTest {

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
    fun budgetLyNavHost(){
        composeRule
            .onNodeWithContentDescription("Accounts Screen")
            .assertIsDisplayed()
    }

    @Test
    fun budgetLyNavHost_navigateToOverview_callingNavigate() {
        runBlocking {
            withContext(Dispatchers.Main) {
                navController.navigate(BudgetLyScreen.Transactions.name)
            }
        }
        composeRule
            .onNodeWithContentDescription("Transactions Screen")
            .assertIsDisplayed()
    }

}