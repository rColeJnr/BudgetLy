package com.rick.accounts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.rick.util.TestTags

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
    Box(modifier = Modifier
        .wrapContentSize(Alignment.TopEnd)){
        var expanded by remember { mutableStateOf(false) }

        IconButton(onClick = { expanded = true }  ){
            Icon(imageVector = icon, contentDescription = cDescription)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier.semantics { contentDescription = TestTags.detailsMoreVertItem }) {
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
    onMenuItemClick: (Int) -> Unit
) {
    Box(modifier = Modifier) {
        var expanded by remember { mutableStateOf(false) }

        TextButton(onClick = { expanded = true }, modifier = Modifier.semantics { contentDescription = TestTags.textButtonDropDown }) {
            Text(text = text)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            for (item in items){
                DropdownMenuItem(onClick = { onMenuItemClick(items.indexOf(item)); expanded = false}, modifier = Modifier.semantics { contentDescription = TestTags.dropDownItem }) {
                    Text(text = item)
                }
                Divider()
            }
        }
    }
}