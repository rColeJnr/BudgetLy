package com.rick.budgetly.feature_options.settings.ui

data class SettingsState(
    val darkMode: Boolean = true,
    val currency: String = "",
    val firstScreen: String = "",
    val pushNotifications: Boolean = false,
    val emailNotifications: Boolean = false,
    val password: Boolean = false,
    val language: String = "",
)
