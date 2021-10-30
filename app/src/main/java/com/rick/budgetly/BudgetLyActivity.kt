package com.rick.budgetly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rick.budgetly.feature_account.ui.accounts.AccountLogic
import com.rick.budgetly.ui.theme.BudgetLyTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BudgetLyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BudgetLyTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greetings(name = "Junior")
                }
            }
        }
    }
}

@Composable
fun Greetings(name: String) {
    
    Text(text = "Hello $name")
    
}

@Preview
@Composable
fun PreviewGreetings() {
    Greetings(name = "Junior")
}
