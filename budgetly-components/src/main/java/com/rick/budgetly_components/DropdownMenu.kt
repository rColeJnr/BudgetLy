package com.rick.budgetly_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun IconDropdownMenu(
    icon: ImageVector,
    cDescription: String,
    onMenuItemOneClick: () -> Unit,
    menuItemOneContent: @Composable () -> Unit,
    onMenuItemSecondClick: () -> Unit,
    menuItemSecondContent: @Composable () -> Unit,
    onMenuItemThirdClick: () -> Unit,
    menuItemThirdContent: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
    ) {
        var expanded by remember { mutableStateOf(false) }

        IconButton(onClick = { expanded = true }) {
            Icon(imageVector = icon, contentDescription = cDescription, modifier = Modifier.wrapContentSize())
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.wrapContentSize().semantics { contentDescription = TestTags.detailsMoreVertItem }) {
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

@Composable
fun TextDropdownMenu(
    text: String,
    items: List<String>,
    onDismissRequest: () -> Unit,
    expanded: Boolean,
    onMenuItemClick: (String) -> Unit
) {
    Box(modifier = Modifier) {

        Text(text = text, modifier = Modifier.semantics { contentDescription = TestTags.textButtonDropDown })
        DropdownMenu(expanded = expanded, onDismissRequest = { onDismissRequest() }) {
            for (item in items) {
                DropdownMenuItem(onClick = {
                    onMenuItemClick(item)
                    onDismissRequest()
                }, modifier = Modifier.semantics { contentDescription = TestTags.dropDownItem }) {
                    Text(text = item)
                }
                Divider()
            }
        }
    }
}