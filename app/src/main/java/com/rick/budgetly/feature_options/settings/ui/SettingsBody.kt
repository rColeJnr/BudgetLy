package com.rick.budgetly.feature_options.settings.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rick.budgetly.feature_options.options.UserDetailsBox

@Composable
fun SettingsBody(navController: NavController) {

    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween) {
        UserDetailsBox(name = "Name", isActive = true, profilePic = Icons.Default.Person)
        SettingAndIcon(setting = "Dark/Light Mode", icon = {
            Switch(
                checked = true,
                onCheckedChange = {}
            )
        }) {}
        SettingAndIcon(
            setting = "Currency",
            icon = {
                Icon(
                    imageVector = Icons.Default.Explore,
                    contentDescription = "currency"
                )
            }) {}
        SettingAndIcon(
            setting = "First screen",
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "screen") }) {}
        SettingAndIcon(
            setting = "Push notifications",
            icon = {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "push"
                )
            }) {}
        SettingAndIcon(
            setting = "Email notifications",
            icon = {
                Icon(
                    imageVector = Icons.Default.NotificationsActive,
                    contentDescription = "email"
                )
            }) {}
        SettingAndIcon(
            setting = "Password on/off",
            icon = {
                Icon(
                    imageVector = Icons.Default.Password,
                    contentDescription = "password"
                )
            }) {}
        SettingAndIcon(setting = "Offline mode", icon = {
            Switch(
                checked = true,
                onCheckedChange = {}
            )
        }) {}
        SettingAndIcon(setting = "Delete all data", icon = {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "delete data"
            )
        }) {}
        SettingAndIcon(
            setting = "Language",
            icon = {
                Icon(
                    imageVector = Icons.Default.Language,
                    contentDescription = "language"
                )
            }) {}
        SettingAndIcon(
            setting = "Share us",
            icon = { Icon(imageVector = Icons.Default.Share, contentDescription = "share") }) {}
    }

}

@Composable
fun SettingAndIcon(setting: String, icon: @Composable () -> Unit, onClick: () -> Unit) {
    Box(modifier = Modifier
        .padding(8.dp)
        .border(
            width = 2.dp,
            color = Color.DarkGray,
            shape = RoundedCornerShape(16.dp)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(text = setting)
            icon()
        }
    }
}