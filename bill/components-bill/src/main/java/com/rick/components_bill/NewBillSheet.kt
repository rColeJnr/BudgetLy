package com.rick.components_bill

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
import com.rick.bill_data.domain.BillIcon
import com.rick.bill_data.util.TestTags
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewBillSheet(
    modifier: Modifier,
    title: String,
    onTitleChanged: () -> Unit,
    icon: Int,
    onIconChanged: () -> Unit,
    archived: Boolean,
    onChangeArchived: () -> Unit,
    paid: Boolean,
    onChangePaid: () -> Unit,
    saveBill: () -> Unit,
    cancelBill: () -> Unit,
    amount: String,
    onAmountChanged: () -> Unit,
    dueDate: Calendar,
    onDateChanged: (Int, Int, Int) -> Unit,
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
                text = title,
                onTextChange = { onTitleChanged() },
                label = stringResource(R.string.name),
                testTag = TestTags.newBillTitle
            )
            if (title.isNotEmpty()) {
                AnimatedIconRow(
                    iconList = iconList,
                    icon = BillIcon.values()[icon].imageVector,
                    onIconChange = { onIconChanged() },
                    modifier = Modifier.padding(top = BASE_DP_VALUE)
                )
            }

            DefaultInputText(
                text = amount,
                onTextChange = { onAmountChanged() },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                label = stringResource(R.string.amount),
                testTag = TestTags.newBillAmount
            )


            Spacer(modifier = Modifier.height(BASE_DP_VALUE))

            CustomDatePicker(context, dueDate, onDateChanged)

            Spacer(modifier = Modifier.height(BASE_DP_VALUE))

            YesOrNoRow(
                question = stringResource(R.string.is_bill_paid),
                answer = paid
            ) { onChangePaid() }

            Spacer(modifier = Modifier.height(BASE_DP_VALUE))

            YesOrNoRow(
                question = stringResource(R.string.is_bill_archived),
                answer = archived
            ) { onChangeArchived() }

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
                        cancelBill()
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
                        saveBill()
                    }
                ) {
                    Text(text = stringResource(R.string.save))
                }
            }

        }
    }
}

@Composable
private fun CustomDatePicker(context: Context, dueDate: Calendar, onDataChanged: (Int, Int, Int) -> Unit) {
    val dueDateDisplayed = remember {
        mutableStateOf(
            "${dueDate.get(Calendar.DAY_OF_MONTH)}/" +
                    "${dueDate.get(Calendar.MONTH) + 1}/" +
                    "${dueDate.get(Calendar.YEAR)}"
        )
    }
    val timePickerDialog = DatePickerDialog(
        context,
        { _, year, month, day ->
            onDataChanged(year, month + 1, day)
            dueDateDisplayed.value = "$day/${month + 1}/$year"
        },
        dueDate.get(Calendar.YEAR),
        dueDate.get(Calendar.MONTH),
        dueDate.get(Calendar.DAY_OF_MONTH)
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