package com.rick.budgetly.feature_account.ui.accounts.components

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rick.budgetly.feature_account.domain.Account

//@Composable
//fun AccountListTitle(
//    modifier: Modifier = Modifier,
//    title: String,
//    totalBalance: String,
//    onClick: () -> Unit
//) {
//
//    Row(modifier = modifier
//        .fillMaxWidth()
//        .clickable {
//            onClick(
//                // TODO("Expand view")
//            )
//        }) {
//        Text(
//            text = title,
//            style = MaterialTheme.typography.subtitle1
//        )
//        Text(
//            text = totalBalance,
//            style = MaterialTheme.typography.subtitle1,
//            textAlign = TextAlign.End,
//            modifier = modifier.fillMaxWidth(1f)
//        )
//    }
//
//}
//
//@Composable
//fun AccountItem(
//    modifier: Modifier,
//    title: String,
//    balance: String,
//    icon: ImageVector,
//    contentDescription: String?,
//    onClick: () -> Unit
//) {
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .height(42.dp)
//            .clickable {
//                onClick(
//                    // TODO (Navigate to account details screen)
//                )
//            },
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(imageVector = icon, contentDescription = contentDescription)
//        Spacer(modifier = Modifier.width(16.dp))
//        Text(text = title)
//        Text(
//            text = balance,
//            modifier = modifier.fillMaxWidth(),
//            style = MaterialTheme.typography.body1,
//            textAlign = TextAlign.End
//        )
//    }
//}
//
//@ExperimentalAnimationApi
//@Composable
//fun AccountByTypeList(
//    items: List<Account>,
//    visible: Boolean,
//    onClick: () -> Unit
//) {
//    AnimatedVisibility(
//        visible = visible,
//        enter = remember { fadeIn(animationSpec = TweenSpec(300, easing = FastOutLinearInEasing)) },
//        exit = remember { fadeOut(animationSpec = TweenSpec(100, easing = FastOutSlowInEasing)) }
//
//    ) {
//        Column(){
//            repeat(items.size -1) {
//                AccountItem(
//                    modifier = Modifier,
//                    title = items[it].title,
//                    balance = items[it].balance,
//                    icon = Icons.Default.AccountBalance,
//                    contentDescription = "Account balance",
//                    onClick = onClick
//                )
//            }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun PreviewAccountListTitle() {
//    AccountListTitle(title = "Title1", totalBalance = "$2000") {
//    }
//}
//
//@Preview
//@Composable
//fun PreviewAccountItem() {
//    AccountItem(
//        modifier = Modifier,
//        title = "Joaquim",
//        balance = "$2000",
//        icon = Icons.Filled.Money,
//        contentDescription = null,
//        onClick = {
//            Log.d("log", "it was clicked")
//        }
//    )
//}
//
//@ExperimentalAnimationApi
//@Preview
//@Composable
//fun PreviewAccountList() {
////    val list = List<Account>(9) {
//////        Account("name$it", "type$it", "Currency", "${75 * it}", "", "", "", 1)
////    }
////    AccountByTypeList(onClick = { /*TODO*/ }, items = list, visible = true)
//}