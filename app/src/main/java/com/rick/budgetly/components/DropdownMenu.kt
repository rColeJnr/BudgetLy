package com.rick.budgetly.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BaseDropdownMenu(
    icon: ImageVector,
    onMenuItemOneClick: () -> Unit,
    menuItemOneContent: @Composable () -> Unit,
    onMenuItemSecondClick: () -> Unit,
    menuItemSecondContent: @Composable () -> Unit,
    onMenuItemThirdClick: () -> Unit,
    menuItemThirdContent: @Composable () -> Unit,
) {
    Box(modifier = Modifier
        .width(16.dp)
        .wrapContentSize(Alignment.TopEnd)){
        var expanded by remember { mutableStateOf(false) }

        IconButton(onClick = { expanded = true }  ){
            Icon(imageVector = icon, contentDescription = null)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(onClick = { onMenuItemOneClick() }) {
                menuItemOneContent()
            }
            Divider()
            DropdownMenuItem(onClick = { onMenuItemSecondClick() }) {
                menuItemSecondContent()
            }
            Divider()
            DropdownMenuItem(onClick = { onMenuItemThirdClick() }) {
                menuItemThirdContent()
            }
        }
    }
}