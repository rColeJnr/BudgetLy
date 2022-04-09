package com.rick.budgetly.feature_options.settings.ui

sealed class SettingsEvents {

    object ChangeDarkLightMode: SettingsEvents()
    object EnablePushNotifications: SettingsEvents()
    object EnableEmailNotifications: SettingsEvents()
    object ChangeNetworkMode: SettingsEvents()
    object EraseAllData: SettingsEvents()
    object ShareUs: SettingsEvents()
    data class ChangeLanguage(val language: String): SettingsEvents()
    data class ChangePassword(val screen: String): SettingsEvents()
    data class ChangeCurrency(val currency: String): SettingsEvents()
    data class ChangeFirstScreen(val screen: String): SettingsEvents()

}