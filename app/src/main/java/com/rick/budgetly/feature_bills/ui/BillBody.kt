package com.rick.budgetly.feature_bills.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rick.budgetly.BudgetLyContainer
import com.rick.budgetly.components.BaseBottomSheet
import com.rick.budgetly.components.BaseRow
import com.rick.budgetly.feature_account.ui.accountdetails.simpleSnackbar
import com.rick.budgetly.feature_account.ui.components.AccountTopBar
import com.rick.budgetly.feature_account.ui.util.formatAmount
import com.rick.budgetly.feature_bills.domain.Bill
import com.rick.budgetly.feature_bills.domain.BillIcon
import com.rick.budgetly.feature_bills.ui.components.BillDetails
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

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

    val scaffoldState = rememberScaffoldState()

    val bills = viewModel.billsState.value.bills
    val bill: MutableState<Bill?> = remember {
        mutableStateOf(bills.firstOrNull())
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is BudgetLyContainer.ShowError -> Toast.makeText(
                    context,
                    event.message,
                    Toast.LENGTH_SHORT
                ).show()
                BudgetLyContainer.ShowSuccess -> navController.navigateUp()
                is BudgetLyContainer.ShowRestoreSnackbar -> {
                    simpleSnackbar(scaffoldSate = scaffoldState, message = event.message, actionLabel = "Undo"){
                        viewModel.onEvent(BillEvents.RestoreBill)
                    }
                }
            }
        }
    }

    Scaffold(Modifier.fillMaxSize(), scaffoldState = scaffoldState) {
        BaseBottomSheet(
            state = modalBottomSheetState,
            scope = scope,
            navController = navController,
            sheetContent = { NewBillSheet(
                modifier,
                viewModel,
                modalBottomSheetState,
                scope,
            ) }
        ) {
            ScreenContent(
                Modifier,
                bills = bills,
                bill = bill.value,
                onNewBillClick = { scope.launch { modalBottomSheetState.show() } },
                onClickDelete = {
                    viewModel.onEvent(BillEvents.DeleteBill(it))
                    bill.value = null
                },
                onBillClick = { bill.value = it },
                onClickEdit = {
                    viewModel.onEvent(BillEvents.EditBill(it))
                    scope.launch { modalBottomSheetState.show() }
                },
                onClickArchive = { viewModel.onEvent(BillEvents.ChangeIsArchived(it.isArchived)) }
            )
        }
    }

}

@ExperimentalMaterialApi
@Composable
private fun ScreenContent(
    modifier: Modifier,
    bills: List<Bill>,
    bill: Bill?,
    onNewBillClick: () -> Unit,
    onBillClick: (Bill) -> Unit,
    onClickDelete: (Bill) -> Unit,
    onClickEdit: (Bill) -> Unit,
    onClickArchive: (Bill) -> Unit
) {

    Column(modifier = modifier.fillMaxSize()) {

        AccountTopBar(
            modifier = Modifier
                .wrapContentHeight(align = Alignment.Top),
            title = formatAmount(bills.map { bill -> bill.amount }.sum()),
            secondTitle = "Due"
        ) {
            Icon(
                imageVector = Icons.Default.PieChart,
                contentDescription = "PieIcon",
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(26.dp)
            )
        }

        BillDetails(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .wrapContentHeight(align = Alignment.Top),
            bill = bill,
            onClickDelete = onClickDelete,
            onClickEdit = onClickEdit,
            onClickArchive = onClickArchive
        )

        BillsList(
            bills = bills,
            onBillClick = onBillClick,
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(align = Alignment.Bottom)
                .heightIn(min = 0.dp, max = 298.dp)
        )

        AddNewBill(onNewBillClick)
    }
}

@Composable
fun BillsList(
    bills: List<Bill>,
    onBillClick: (Bill) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(bills) {
            BaseRow(
                modifier = Modifier
                    .clickable {
                        onBillClick(it)
                    }
                    .semantics { contentDescription = "BillRow" },
                icon = BillIcon.values()[it.icon].imageVector,
                arrowIcon = Icons.Default.KeyboardArrowUp,
                title = it.title,
                bottomRowText = "${it.dueDate.get(Calendar.DAY_OF_MONTH)} " +
                        "${it.dueDate.get(Calendar.MONTH)} " +
                        "${it.dueDate.get(Calendar.YEAR)}",
                midRowText = if (it.isPaid) "paid" else "due",
                balance = it.amount
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun AddNewBill(
    onClick: () -> Unit
) {

    TextButton(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Bottom)
            .padding(vertical = 8.dp)
    ) {
        Icon(imageVector = Icons.Default.PlusOne, contentDescription = "Add new bill")
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Add a new Bill", textAlign = TextAlign.Start)
    }

}
