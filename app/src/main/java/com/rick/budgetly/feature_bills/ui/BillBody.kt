package com.rick.budgetly.feature_bills.ui

import android.app.DatePickerDialog
import android.content.Context
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rick.budgetly.components.BaseBottomSheet
import com.rick.budgetly.components.BaseRow
import com.rick.budgetly.feature_account.domain.AccountCurrency
import com.rick.budgetly.feature_account.domain.AccountIcon
import com.rick.budgetly.feature_account.ui.accountneworedit.components.AnimatedIconRow
import com.rick.budgetly.feature_account.ui.components.AccountTopBar
import com.rick.budgetly.feature_account.ui.components.DefaultInputText
import com.rick.budgetly.feature_bills.domain.Bill
import com.rick.budgetly.feature_bills.domain.BillIcon
import com.rick.budgetly.feature_bills.util.TestTags
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BillsBody(
    modifier: Modifier = Modifier,
    viewModel: BillViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    BaseBottomSheet(
        state = modalBottomSheetState,
        sheetContent = { NewBillSheet(modifier, viewModel) }
    ) {
        ScreenContent(Modifier, viewModel) {
            scope.launch { modalBottomSheetState.show() }
        }
    }

}

@Composable
fun NewBillSheet(modifier: Modifier, viewModel: BillViewModel) {
    val context = LocalContext.current
    val iconList = mutableListOf<ImageVector>()
    for (icon in BillIcon.values()){
        iconList.add(icon.imageVector)
    }
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
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
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
            ) { viewModel.onEvent(BillEvents.ChangeIsPaid(viewModel.billIsPaid.value)) }

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
                    ), onClick = { viewModel.onEvent(BillEvents.CancelNewBill) }) {
                    Text(text = "Cancel")
                }
                TextButton(
                    modifier = Modifier.border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    ), onClick = { viewModel.onEvent(BillEvents.SaveBill) }) {
                    Text(text = "Save")
                }
            }

        }
    }
}

@Composable
private fun YesOrNoRow(question: String, answer: Boolean, onClick: () -> Unit) {
    val yayOrNay = remember { mutableStateOf(answer) }
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(horizontal = 16.dp, vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = question, style = MaterialTheme.typography.body1)
        Text(text = if (yayOrNay.value) "Yes" else "No", style = MaterialTheme.typography.body1)
    }
}

@Composable
private fun CustomDatePicker(context: Context, viewModel: BillViewModel) {
    val cal = Calendar.getInstance()
    val timePickerDialog = DatePickerDialog(
        context,
        { _, year, month, day ->
            viewModel.onEvent(
                BillEvents.EnteredDueDate(year, month, day)
            )
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
    )

    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(text = "Due date", modifier = Modifier.padding(horizontal = 16.dp))
    }
    TextButton(
        onClick = { timePickerDialog.show() },
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Text(text = "${ viewModel.billDueDate.get(Calendar.YEAR) } ${ viewModel.billDueDate.get(Calendar.MAY) } ${ viewModel.billDueDate.get(Calendar.DAY_OF_MONTH) }", style = MaterialTheme.typography.body1)
    }
}

@ExperimentalMaterialApi
@Composable
private fun ScreenContent(
    modifier: Modifier,
    viewModel: BillViewModel,
    onNewBillClick: () -> Unit,
) {

    val bills = viewModel.billsState.value.bills

    Column(modifier = modifier.fillMaxSize()) {

        AccountTopBar(
            modifier = Modifier
                .weight(1f)
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
                bottomRowText = it.dueDate.get(Calendar.YEAR).toString(),
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


