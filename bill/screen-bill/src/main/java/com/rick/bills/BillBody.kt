package com.rick.bills

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rick.bill_data.domain.Bill
import com.rick.bill_data.domain.BillIcon
import com.rick.components_bill.BaseBottomSheet
import com.rick.components_bill.BillDetails
import com.rick.components_bill.NewBillSheet
import com.rick.core.BudgetLyContainer
import com.rick.core.formatAmount
import com.rick.screen_bill.R
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
    val bill: Bill = viewModel.viewModelBill.value!!

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is BudgetLyContainer.ShowError -> Toast.makeText(
                    context,
                    event.message,
                    Toast.LENGTH_SHORT
                ).show()

                BudgetLyContainer.ShowSuccess -> { modalBottomSheetState.hide() }

                is BudgetLyContainer.ShowRestoreSnackbar -> {
                    simpleSnackbar(
                        scaffoldSate = scaffoldState,
                        message = event.message,
                        actionLabel = context.getString(R.string.undo)
                    ) {
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
            controlNavigation = { TODO("bottom sheet navigation") },
            sheetContent = {
                NewBillSheet(
                    modifier,
                    viewModel,
                    modalBottomSheetState,
                    scope,
                )
            }
        ) {
            ScreenContent(
                Modifier,
                bills = bills,
                bill = bill,
                onNewBillClick = { scope.launch { modalBottomSheetState.show() } },
                onClickDelete = {
                    viewModel.onEvent(BillEvents.DeleteBill(it))
                },
                onBillClick = { viewModel.onEvent(BillEvents.BillSelected(it)) },
                onClickEdit = {
                    viewModel.onEvent(BillEvents.EditBill(it))
                    scope.launch { modalBottomSheetState.show() }
                },
                onClickArchive = {
                    viewModel.onEvent(BillEvents.ChangeIsArchived(it.isArchived)) }
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
            secondTitle = stringResource(R.string.due)
        ) {
            Icon(
                imageVector = Icons.Default.PieChart,
                contentDescription = Icons.Default.PieChart.name,
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

    val context = LocalContext.current
    LazyColumn(
        modifier = modifier
    ) {
        items(bills) {
            BaseRow(
                modifier = Modifier
                    .clickable {
                        onBillClick(it)
                    }
                    .semantics { contentDescription = context.getString(R.string.bill_row) },
                icon = BillIcon.values()[it.icon].imageVector,
                arrowIcon = Icons.Default.KeyboardArrowUp,
                title = it.title,
                bottomRowText = "${it.dueDate.get(Calendar.DAY_OF_MONTH)} " +
                        "${it.dueDate.get(Calendar.MONTH)} " +
                        "${it.dueDate.get(Calendar.YEAR)}",
                midRowText = if (it.isPaid) stringResource(R.string.paid) else stringResource(id = R.string.due),
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
        Icon(imageVector = Icons.Default.PlusOne, contentDescription = stringResource(R.string.add_new_bill))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = stringResource(id = R.string.add_new_bill), textAlign = TextAlign.Start)
    }

}