package com.rick.budgetly.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.max

// I created the layout, but i have no idea how to implement on click
@Composable
fun CustomRow(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    rows: Int = 4,
    content: @Composable () -> Unit,
) {

    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->

        // Keep track of the max height of each row
        val rowHeights = IntArray(rows) { 0 }
        // Keep track of the max height of each row
        val rowWidths = IntArray(rows) { 0 }
        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeables = measurables.mapIndexed { index, measurable ->

            val placeable = measurable.measure(constraints)
            val row = index % rows
            // Measure each child
            rowHeights[row] = max(rowHeights[row], placeable.height)
            rowWidths[row] = placeable.width
            placeable
        }

        // Track the y co-ord we have placed children up to
        val rowY = IntArray(rows) { 0 }
        for (i in 1 until rows) {
            rowY[i] = rowY[i - 1] + rowHeights[i - 1]
        }

        // Grid's width is the widest row
        val rowX = IntArray(rows) { 0 }
        for (i in 1 until rows) {
            rowX[i] = rowX[i - 1] + rowWidths[i - 1]
        }

        // Grid's height is the sum of the tallest element of each row
        // coerced to the height constraints
        val height = rowHeights.sumOf { it }
            .coerceIn(constraints.minHeight.rangeTo(constraints.maxHeight))

        val width = rowWidths.sumOf { it } - rowWidths[0]
            .coerceIn(constraints.minWidth.rangeTo(constraints.maxWidth))

        // Set the size of the layout as big as the children
        layout(width, height) {
            val rowX = IntArray(rows) { 0 }
            // Place children in the parent layout
            placeables.forEachIndexed { index, placeable ->
                // Position item on the screen
                val row = index % rows
                placeable.placeRelative(x = rowX[row], y = rowY[row])
                rowX[row] += placeable.width
            }
        }
    }
}

val lilst = listOf(
    "7", "4", "1", ".", "8", "5", "2", "0", "9", "6", "3", "<"
)
val list = listOf(
    "+", "-", "x", "/", "="
)

@Composable
fun Calculator() {
    Column(Modifier.wrapContentWidth(align = Alignment.CenterHorizontally)) {
        val text = mutableStateOf("343")
        val operator = mutableStateOf("")
        val num1 = mutableStateOf("")
        val num2 = mutableStateOf("")
//        var result = ""
//        TextField(
//            value = text,
//            onValueChange = onTextChange,
//            color = Color.White
//        )
        CustomRow(
            modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally),
            rows = 1,
            onClick = {}
        ) {
            for (i in list) {
                TextButton(
                    onClick = {
                        if (i != "=") {
                            operator.value = i
                            num1.value = text.value
//                            onTextChange = ""
                        } else {
                            num2.value = text.value
//                            text = calculate(num1.value, num2.value, Operators.valueOf(operator.value))
                        }
                    }, modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally)
                ) {
                    Text(text = i)
                }
            }
        }
        CustomRow(
            modifier = Modifier
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .padding(start = 8.dp),
            onClick = {}
        ) {
            for (i in lilst) {
                TextButton(
                    onClick = {
                        text.value += i
                    },
                    modifier = Modifier
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                        .widthIn(min = 100.dp, max = 140.dp)
                ) {
                    Text(text = i)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCalculator() {
    Calculator()
}