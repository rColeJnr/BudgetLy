package com.rick.accounts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
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
