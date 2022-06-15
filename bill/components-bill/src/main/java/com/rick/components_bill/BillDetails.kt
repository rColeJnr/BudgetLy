package com.rick.components_bill

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rick.bill_data.domain.Bill
import java.util.*

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
        .padding(vertical = 4.dp, horizontal = 8.dp), verticalArrangement = Arrangement.SpaceBetween) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Column {
                Text(text = bill?.title ?: "title", style = MaterialTheme.typography.h5)
                Text(text = "${bill?.amount ?: "amount"}", style = MaterialTheme.typography.h5)
            }
            Text(text = "${bill?.dueDate?.get(Calendar.MONTH) ?: "due"}/${bill?.dueDate?.get(
                Calendar.DAY_OF_MONTH) ?: "date"}", style = MaterialTheme.typography.h5, fontWeight = FontWeight.SemiBold)
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