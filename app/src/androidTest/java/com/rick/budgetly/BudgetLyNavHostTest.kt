package com.rick.budgetly

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
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
                navController.navigate(BudgetLyScreen.Bills.name)
            }
        }
    }

}