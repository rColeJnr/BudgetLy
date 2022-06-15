package com.rick.budgetly_components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedColorsRow(
    colorsVisible: Boolean,
    colors: List<Color>,
    color: Color,
    onColorChange: (Color) -> Unit,
    modifier: Modifier = Modifier
) {
    val entry = remember { fadeIn(animationSpec = TweenSpec(300, easing = FastOutLinearInEasing)) }
    val exit = remember { fadeOut(animationSpec = TweenSpec(100, easing = FastOutSlowInEasing)) }

    Box(modifier = modifier.defaultMinSize(minHeight = 16.dp)) {
        AnimatedVisibility(visible = colorsVisible, enter = entry, exit = exit) {
            ColorsRow(colors = colors, color = color, onColorChange = onColorChange, modifier)
        }
    }
}

@Composable
fun ColorsRow(
    colors: List<Color>,
    color: Color,
    onColorChange: (Color) -> Unit,
    modifier: Modifier
) {
    Row(Modifier.horizontalScroll(rememberScrollState())) {
        for (accountColor in colors) {
            SelectableColorButton(
                color = accountColor,
                onColorSelected = { onColorChange(accountColor) },
                isSelected = accountColor == color,
                modifier = modifier
            )
        }
    }
}

@Composable
fun SelectableColorButton(
    color: Color,
    onColorSelected: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {

    TextButton(onClick = { onColorSelected() }, modifier = modifier) {
        Box(
            modifier = Modifier
                .padding(3.dp)
                .size(50.dp)
                .background(color, shape = CircleShape)
                .border(
                    width = 3.dp,
                    color = if (isSelected) colors.onPrimary else Color.Transparent,
                    shape = CircleShape
                )
        )
    }
}

@Composable
fun AnimatedIconRow(
    iconList: List<ImageVector>,
    icon: ImageVector,
    onIconChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    visible: Boolean = true
) {
    val enter = remember { fadeIn(animationSpec = TweenSpec(300, easing = FastOutLinearInEasing)) }
    val exit = remember { fadeOut(animationSpec = TweenSpec(100, easing = FastOutSlowInEasing)) }
    Box(modifier.defaultMinSize(minHeight = 16.dp)) {
        AnimatedVisibility(
            visible = visible,
            enter = enter,
            exit = exit,
        ) {
            IconRow(icon, iconList, onIconChange)
        }
    }
}

@Composable
fun IconRow(
    icon: ImageVector,
    icons: List<ImageVector>,
    onIconChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier.horizontalScroll(rememberScrollState())) {
        for ((index, thisIcon) in icons.withIndex()) {
            SelectableIconButton(
                icon = thisIcon,
                cDescription = thisIcon.name,
                onIconSelected = { onIconChange(index) },
                isSelected = thisIcon == icon
            )
        }
    }
}

@Composable
fun SelectableIconButton(
    icon: ImageVector,
    cDescription: String,
    onIconSelected: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    //another mathematica onde fazemos essa cena de novo.
    val tint = if (isSelected) {
        colors.primary
    } else colors.onSurface.copy(alpha = 0.6f)

    TextButton(onClick = { onIconSelected() }, shape = CircleShape, modifier = modifier) {
        Column {
            Icon(
                imageVector = icon,
                tint = tint,
                contentDescription = cDescription
            )
            if (isSelected) {
                Box(
                    Modifier
                        .padding(top = 3.dp)
                        .width(icon.defaultWidth)
                        .height(1.dp)
                        .background(tint)
                )
            } else Spacer(Modifier.height(2.dp))
        }
    }
}