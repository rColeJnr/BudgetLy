package com.rick.budgetly_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DefaultRow(
    text: String,
    image: ImageVector,
    description: String,
    element: @Composable () -> Unit = {},
    padding: Dp = PADDING,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .border(
                width = 2.dp,
                color = Color.DarkGray,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable { onClick() }
                .padding(vertical = padding, horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.body1,
                fontFamily = FontFamily.SansSerif
            )
            element()
            Image(
                imageVector = image,
                contentDescription = description,
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}

private val PADDING = 12.dp

@Preview
@Composable
private fun PrevDRow() {
    DefaultRow(
        text = "cjfal",
        image = Icons.Default.CheckBox,
        description = "Text",
        element = {
            Switch(
                checked = true,
                onCheckedChange = {}
            )
        },
        padding = 8.dp,
        onClick = {}
    )
}