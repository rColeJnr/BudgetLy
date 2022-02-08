package com.rick.budgetly.feature_bills.ui

import android.app.DatePickerDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rick.budgetly.BudgetLyContainer
import com.rick.budgetly.components.BaseBottomSheet
import com.rick.budgetly.components.BaseRow
import com.rick.budgetly.components.SimpleSnackbar
import com.rick.budgetly.feature_account.ui.accountneworedit.components.AnimatedIconRow
import com.rick.budgetly.feature_account.ui.components.AccountTopBar
import com.rick.budgetly.feature_account.ui.components.DefaultInputText
import com.rick.budgetly.feature_bills.domain.Bill
import com.rick.budgetly.feature_bills.domain.BillIcon
import com.rick.budgetly.feature_bills.util.TestTags
import kotlinx.coroutines.CoroutineScope
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
                    viewModel.snackbarMessage.value = event.message
                    viewModel.showSnackbar.value = true
                }
            }
        }
    }
    BaseBottomSheet(
        state = modalBottomSheetState,
        scope = scope,
        navController = navController,
        sheetContent = { NewBillSheet(modifier, viewModel, modalBottomSheetState, scope) }
    ) {
        ScreenContent(
            Modifier,
            viewModel,
            onClickDelete = { viewModel.onEvent(BillEvents.DeleteBill(it)) },
            onClickEdit = {
                viewModel.viewModelBill.value = it; scope.launch { modalBottomSheetState.show() }
            },
            onClickArchive = { viewModel.onEvent(BillEvents.ChangeIsArchived(it.isArchived)) },
            onNewBillClick ={ scope.launch { modalBottomSheetState.show() } }
        )
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewBillSheet(
    modifier: Modifier,
    viewModel: BillViewModel,
    state: ModalBottomSheetState,
    scope: CoroutineScope
) {
    val context = LocalContext.current
    val iconList = mutableListOf<ImageVector>()
    for (icon in BillIcon.values()) {
        iconList.add(icon.imageVector)
    }
    val hideSheet = { scope.launch { state.hide() } }
    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.Top)
        ) {
            DefaultInputText(
                text = viewModel.billTitle.value,
                onTextChange = { viewModel.onEvent(BillEvents.EnteredTitle(it)) },
                label = "Name",
                testTag = TestTags.newBillTitle
            )
            if (viewModel.billTitle.value.isNotEmpty()) {
                AnimatedIconRow(
                    iconList = iconList,
                    icon = BillIcon.values()[viewModel.billIcon.value].imageVector,
                    onIconChange = { viewModel.onEvent(BillEvents.ChangeBillIcon(it)) },
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            DefaultInputText(
                text = viewModel.billAmount.value,
                onTextChange = { viewModel.onEvent(BillEvents.EnteredAmount(it)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                label = "Amount",
                testTag = TestTags.newBillAmount
            )


            Spacer(modifier = Modifier.height(8.dp))

            CustomDatePicker(context, viewModel)

            Spacer(modifier = Modifier.height(8.dp))

            YesOrNoRow(
                question = "Is bill paid",
                answer = viewModel.billIsPaid.value
            ) { viewModel.onEvent(BillEvents.ChangeIsPaid(viewModel.billIsPaid.value)) }

            Spacer(modifier = Modifier.height(8.dp))

            YesOrNoRow(
                question = "Is bill archived",
                answer = viewModel.billArchived.value
            ) { viewModel.onEvent(BillEvents.ChangeIsArchived(viewModel.billArchived.value)) }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                TextButton(
                    modifier = Modifier.border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    ), onClick = {
                        viewModel.onEvent(BillEvents.CancelNewBill)
                        hideSheet()
                    }
                ) {
                    Text(text = "Cancel")
                }
                TextButton(
                    modifier = Modifier.border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    ), onClick = {
                        viewModel.onEvent(BillEvents.SaveBill)
                        hideSheet()
                    }
                ) {
                    Text(text = "Save")
                }
            }

        }
    }
}

@Composable
private fun YesOrNoRow(question: String, answer: Boolean, onClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(horizontal = 16.dp, vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = question, style = MaterialTheme.typography.body1)
        Text(text = if (answer) "Yes" else "No", style = MaterialTheme.typography.body1)
    }
}

@Composable
private fun CustomDatePicker(context: Context, viewModel: BillViewModel) {
    val cal = viewModel.billDueDate
    val dueDateDisplayed = remember {
        mutableStateOf(
            "${cal.get(Calendar.DAY_OF_MONTH)}/" +
                    "${cal.get(Calendar.MONTH) + 1}/" +
                    "${cal.get(Calendar.YEAR)}"
        )
    }
    val timePickerDialog = DatePickerDialog(
        context,
        { _, year, month, day ->
            viewModel.onEvent(
                BillEvents.EnteredDueDate(year, month + 1, day)
            )
            dueDateDisplayed.value = "$day/${month + 1}/$year"
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
    )

    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(text = "Due date", modifier = Modifier.padding(horizontal = 16.dp))
    }
    TextButton(
        onClick = { timePickerDialog.show() },
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Text(
            text = dueDateDisplayed.value,
            style = MaterialTheme.typography.body1
        )
    }
}

@ExperimentalMaterialApi
@Composable
private fun ScreenContent(
    modifier: Modifier,
    viewModel: BillViewModel,
    onNewBillClick: () -> Unit,
    onClickDelete: (Bill) -> Unit,
    onClickEdit: (Bill) -> Unit,
    onClickArchive: (Bill) -> Unit
) {

    val bills = viewModel.billsState.value.bills
    var bill: MutableState<Bill?> = remember {
        mutableStateOf(bills.firstOrNull())
    }

    Column(modifier = modifier.fillMaxSize()) {

        AccountTopBar(
            modifier = Modifier
//                .weight()
                .wrapContentHeight(align = Alignment.Top),
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
        }

        BillDetails(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .wrapContentHeight(align = Alignment.Top),
            bill = bill.value,
            onClickDelete = onClickDelete,
            onClickEdit = onClickEdit,
            onClickArchive = onClickArchive
        )

        BillsList(
            bills = bills,
            onBillClick = { bill.value = it },
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(align = Alignment.Bottom)
                .heightIn(min = 0.dp, max = 298.dp)
        )

        AddNewBill(onNewBillClick)
        SimpleSnackbar(
            show = viewModel.showSnackbar.value,
            message = viewModel.snackbarMessage.value
        ) {
            viewModel.onEvent(BillEvents.RestoreBill)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BillDetails(
    modifier: Modifier,
    bill: Bill? = null,
    onClickDelete: (Bill) -> Unit,
    onClickEdit: (Bill) -> Unit,
    onClickArchive: (Bill) -> Unit,
) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(vertical = 4.dp), verticalArrangement = Arrangement.SpaceBetween) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Column {
                Text(text = bill?.title ?: "title", style = MaterialTheme.typography.h5)
                Text(text = "${bill?.amount ?: "amount"}", style = MaterialTheme.typography.h5)
            }
            Text(text = "${bill?.dueDate?.get(Calendar.MONTH) ?: "due"}/${bill?.dueDate?.get(Calendar.DAY_OF_MONTH) ?: "date"}", style = MaterialTheme.typography.h5, fontWeight = FontWeight.SemiBold)
        }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            bill?.let { Text(text = if (bill.isPaid) "PAID" else "NOT PAID") }
                ?: Text(text = "Payment status") // due in many days
            bill?.let { Text(text = if (bill.isArchived) "Bill archived" else "Bill active") }
                ?: Text(text = "Bill status")
        }


        AnimatedVisibility(visible = bill != null) {
            if (bill == null) return@AnimatedVisibility
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.clickable { onClickDelete(bill) },
                        text = "delete",
                        style = MaterialTheme.typography.body2
                    )
                    Text(
                        modifier = Modifier.clickable { onClickEdit(bill) },
                        text = "edit",
                        style = MaterialTheme.typography.body2
                    )
                    Text(
                        modifier = Modifier.clickable { onClickArchive(bill) },
                        text = if (bill.isArchived) "activate" else "archive",
                        style = MaterialTheme.typography.body2
                    )
                }
        }

    }
    // delete and archive

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


