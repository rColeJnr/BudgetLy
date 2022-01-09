package com.rick.budgetly.feature_bills.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rick.budgetly.components.BaseBottomSheet
import com.rick.budgetly.components.BaseRow
import com.rick.budgetly.feature_account.domain.AccountCurrency
import com.rick.budgetly.feature_account.ui.accountneworedit.components.AnimatedIconRow
import com.rick.budgetly.feature_account.ui.components.AccountInputText
import com.rick.budgetly.feature_account.ui.components.AccountTopBar
import com.rick.budgetly.feature_bills.domain.Bill
import com.rick.budgetly.feature_bills.domain.BillIcon
import com.rick.budgetly.feature_bills.util.TestTags
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BillsBody(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: BillViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    BaseBottomSheet(
        state = modalBottomSheetState,
        scope = scope,
        sheetContent = { NewBillSheet(viewModel) }
    ) {
        ScreenContent(viewModel, scope) {}
    }

}

@Composable
fun NewBillSheet(viewModel: BillViewModel) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            AccountInputText(
                text = viewModel.billTitle.value,
                onTextChange = {
                    viewModel.onEvent(
                        BillEvents.EnteredTitle(it)
                    )
                },
                testTag = TestTags.newBillTitle
            )
            if (viewModel.billTitle.value.isNotEmpty()){
                AnimatedIconRow(
                    icon = BillIcon.values()[viewModel.billIcon.value].imageVector,
                    onIconChange = { viewModel.onEvent(BillEvents.ChangeBillIcon(it)) },
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            AccountInputText(
                text = viewModel.billAmount.value.toString(),
                onTextChange = { viewModel.onEvent(BillEvents.EnteredAmount(it.toFloat())) },
                testTag = TestTags.newBillAmount
            )


            Spacer(modifier = Modifier.height(8.dp))

            AccountInputText(
                text = viewModel.billDueDate.value.toString(),
                onTextChange = { viewModel.onEvent(BillEvents.EnteredDueDate(it.toLong())) },
                testTag = TestTags.newBillAmount
            )

        }
    }

}

@ExperimentalMaterialApi
@Composable
private fun ScreenContent(
    viewModel: BillViewModel,
    scope: CoroutineScope,
    onNewBillClick: () -> Unit,
) {

    val bills = viewModel.billsState.value.bills

    Column(modifier = Modifier.fillMaxSize()) {

        AccountTopBar(
            modifier = Modifier.wrapContentHeight(),
            title = "13274",
            secondTitle = "Due"
        ) {
            Icon(
                imageVector = Icons.Default.PieChart,
                contentDescription = "PieIcon",
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(26.dp)
            )

            BillsList(
                bills = bills,
                onBillClick = {},
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(align = Alignment.Bottom)
                    .heightIn(min = 0.dp, max = 298.dp)
            )
            AddNewBill(viewModel, onNewBillClick)
        }

    }
}

@Composable
fun BillsList(
    bills: List<Bill>,
    onBillClick: () -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(bills) {
            BaseRow(
                modifier = Modifier
                    .clickable {
                        onBillClick()
                    }
                    .semantics { contentDescription = "BillRow" },
                icon = BillIcon.values()[it.icon].imageVector,
                title = it.title,
                currency = AccountCurrency.USD.currency,
                balance = it.amount
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun AddNewBill(
    viewModel: BillViewModel,
    onClick: () -> Unit
) {

    TextButton(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Bottom)
            .padding(bottom = 8.dp)
    ) {
        Icon(imageVector = Icons.Default.PlusOne, contentDescription = "Add new bill")
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Add a new Bill", textAlign = TextAlign.Start)
    }

}


