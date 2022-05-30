package com.rick.settings

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SettingsBody(navController: NavController) {

    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween) {
        UserDetailsBox(name = stringResource(id = R.string.name), isActive = true, profilePic = Icons.Default.Person)
        SettingAndIcon(setting = stringResource(R.string.dark_light_mode), icon = {
            Switch(
                checked = true,
                onCheckedChange = {}
            )
        }) {}
        SettingAndIcon(
            setting = stringResource(id = R.string.Currency),
            icon = {
                Icon(
                    imageVector = Icons.Default.Explore,
                    contentDescription = stringResource(id = R.string.Currency)
                )
            }) {}
        SettingAndIcon(
            setting = stringResource(R.string.first_screen),
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = stringResource(R.string.screen)) }) {}
        SettingAndIcon(
            setting = stringResource(R.string.push_notification),
            icon = {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = stringResource(id = R.string.push_notification)
                )
            }) {}
        SettingAndIcon(
            setting = stringResource(R.string.email_notifications),
            icon = {
                Icon(
                    imageVector = Icons.Default.NotificationsActive,
                    contentDescription = stringResource(id = R.string.email_notifications)
                )
            }) {}
        SettingAndIcon(
            setting = stringResource(R.string.password_on_off),
            icon = {
                Icon(
                    imageVector = Icons.Default.Password,
                    contentDescription = stringResource(R.string.password_on_off)
                )
            }) {}
        SettingAndIcon(setting = stringResource(R.string.offline_mode), icon = {
            Switch(
                checked = true,
                onCheckedChange = {}
            )
        }) {}
        SettingAndIcon(setting = stringResource(R.string.delete_data), icon = {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.delete_data)
            )
        }) {}
        SettingAndIcon(
            setting = stringResource(R.string.language),
            icon = {
                Icon(
                    imageVector = Icons.Default.Language,
                    contentDescription = stringResource(R.string.language)
                )
            }) {}
        SettingAndIcon(
            setting = stringResource(R.string.share_us),
            icon = { Icon(imageVector = Icons.Default.Share, contentDescription = stringResource(R.string.share_us)) }) {}
    }

}

@Composable
fun UserDetailsBox(name: String, isActive: Boolean, profilePic: Any) {
    TODO("Not yet implemented")
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