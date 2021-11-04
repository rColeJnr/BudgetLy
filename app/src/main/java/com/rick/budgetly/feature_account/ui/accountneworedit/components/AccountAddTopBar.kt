package com.rick.budgetly.feature_account.ui.accountneworedit.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rick.budgetly.feature_account.domain.AccountColor
import com.rick.budgetly.feature_account.domain.AccountIcon

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedColorsRow(
    colorsVisible: Boolean,
    color: AccountColor,
    onColorChange: (AccountColor) -> Unit,
    modifier: Modifier = Modifier
) {
    val entry = remember { fadeIn(animationSpec = TweenSpec(300, easing = FastOutLinearInEasing)) }
    val exit = remember { fadeOut(animationSpec = TweenSpec(100, easing = FastOutSlowInEasing)) }

    Box(modifier = modifier.defaultMinSize(minHeight = 16.dp)){
        AnimatedVisibility(visible = colorsVisible, enter = entry, exit = exit) {
            ColorsRow(color = color, onColorChange = onColorChange, modifier)
        }
    }
}

@Composable
fun ColorsRow(color: AccountColor, onColorChange: (AccountColor) -> Unit, modifier: Modifier) {
    Row(Modifier.horizontalScroll(rememberScrollState())) {
        for (accountColor in AccountColor.values()){
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
    color: AccountColor,
    onColorSelected: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {

    TextButton(onClick = {onColorSelected()}, modifier = modifier) {
        Box(modifier = Modifier
            .padding(3.dp)
            .size(50.dp)
            .background(color.color, shape = CircleShape)
            .border(
                width = 3.dp,
                color = if (isSelected) MaterialTheme.colors.onPrimary else Color.Transparent,
                shape = CircleShape
            )
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedIconRow(
    icon: AccountIcon,
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
            IconRow(icon, onIconChange)
        }
    }
}

@Composable
fun IconRow(
    icon: AccountIcon,
    onIconChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier.horizontalScroll(rememberScrollState())) {
        for (accountIcon in AccountIcon.values()) {
            SelectableIconButton(
                icon = accountIcon.imageVector,
                cDescription = accountIcon.contentDescription,
                onIconSelected = { onIconChange },
                isSelected = accountIcon == icon
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
        MaterialTheme.colors.primary
    } else MaterialTheme.colors.onSurface.copy(alpha = 0.6f)

    TextButton(onClick = { onIconSelected() }, shape = CircleShape, modifier = modifier) {
        Column {
            Icon(
                imageVector = icon,
                tint = tint,
                contentDescription = icon.name
            )
            if (isSelected) {
                Box(
                    Modifier
                        .padding(top = 3.dp)
                        .width(icon.defaultWidth)
                        .height(1.dp)
                        .background(tint)
                )
            } else Spacer(Modifier.height(4.dp))
        }
    }
}