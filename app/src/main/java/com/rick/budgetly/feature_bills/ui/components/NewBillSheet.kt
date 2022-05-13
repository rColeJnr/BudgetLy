package com.rick.budgetly.feature_bills.ui.components

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.rick.budgetly.R
import com.rick.budgetly.feature_account.ui.accountneworedit.components.AnimatedIconRow
import com.rick.budgetly.feature_account.ui.components.DefaultInputText
import com.rick.budgetly.feature_bills.domain.BillIcon
import com.rick.budgetly.feature_bills.ui.BillEvents
import com.rick.budgetly.feature_bills.ui.BillViewModel
import com.rick.budgetly.feature_bills.util.TestTags
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewBillSheet(
    modifier: Modifier,
    viewModel: BillViewModel,
    state: ModalBottomSheetState,
    scope: CoroutineScope,
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
                label = stringResource(R.string.name),
                testTag = TestTags.newBillTitle
            )
            if (viewModel.billTitle.value.isNotEmpty()) {
                AnimatedIconRow(
                    iconList = iconList,
                    icon = BillIcon.values()[viewModel.billIcon.value].imageVector,
                    onIconChange = { viewModel.onEvent(BillEvents.ChangeBillIcon(it)) },
                    modifier = Modifier.padding(top = BASE_DP_VALUE)
                )
            }

            DefaultInputText(
                text = viewModel.billAmount.value,
                onTextChange = { viewModel.onEvent(BillEvents.EnteredAmount(it)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                label = stringResource(R.string.amount),
                testTag = TestTags.newBillAmount
            )


            Spacer(modifier = Modifier.height(BASE_DP_VALUE))

            CustomDatePicker(context, viewModel)

            Spacer(modifier = Modifier.height(BASE_DP_VALUE))

            YesOrNoRow(
                question = stringResource(R.string.is_bill_paid),
                answer = viewModel.billIsPaid.value
            ) { viewModel.onEvent(BillEvents.ChangeIsPaid(viewModel.billIsPaid.value)) }

            Spacer(modifier = Modifier.height(BASE_DP_VALUE))

            YesOrNoRow(
                question = stringResource(R.string.is_bill_archived),
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
                        shape = RoundedCornerShape(BASE_DP_VALUE)
                    ), onClick = {
                        viewModel.onEvent(BillEvents.CancelNewBill)
                        hideSheet()
                    }
                ) {
                    Text(text = stringResource(R.string.cancel))
                }
                TextButton(
                    modifier = Modifier.border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(BASE_DP_VALUE)
                    ), onClick = {
                        viewModel.onEvent(BillEvents.SaveBill)
                    }
                ) {
                    Text(text = stringResource(R.string.save))
                }
            }

        }
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
        Text(text = stringResource(R.string.due_date), modifier = Modifier.padding(horizontal = 16.dp))
    }
    TextButton(
        onClick = { timePickerDialog.show() },
        modifier = Modifier.padding(horizontal = BASE_DP_VALUE)
    ) {
        Text(
            text = dueDateDisplayed.value,
            style = MaterialTheme.typography.body1
        )
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
        Text(text = if (answer) stringResource(R.string.yes) else stringResource(R.string.no), style = MaterialTheme.typography.body1)
    }
}

private val BASE_DP_VALUE = 8.dp