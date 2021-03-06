package com.rick.budgetly_components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rick.core.formatAmount

@Composable
fun BaseRow(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    arrowIcon: ImageVector = Icons.Default.ChevronRight,
    title: String,
    bottomRowText: String,
    midRowText: String = "",
    balance: Float,
) {
    val formattedAmount = formatAmount(balance)

    Card(modifier = modifier
        .height(68.dp)
        .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val typography = MaterialTheme.typography
            Icon(imageVector = icon, contentDescription = title, modifier = Modifier.padding(start = 8.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Column(Modifier.wrapContentWidth()) {
                Text(
                    text = title,
                    style = typography.body1
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text = bottomRowText, style = typography.subtitle1)
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = midRowText,
                style = typography.h6,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = formattedAmount,
                style = typography.h6,
                modifier = Modifier.align(Alignment.CenterVertically),
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.width(8.dp))
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Icon(imageVector = arrowIcon, contentDescription = null, modifier = Modifier
                    .padding(end = 12.dp)
                    .size(24.dp))
            }
        }
    }
    Divider(Modifier.height(8.dp))
}