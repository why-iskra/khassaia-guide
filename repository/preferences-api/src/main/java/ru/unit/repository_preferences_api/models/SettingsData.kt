package ru.unit.repository_preferences_api.models

data class SettingsData(
    val syncWithSystemTheme: Boolean,
    val nightTheme: Boolean,
    val mapSyncWithAppTheme: Boolean,
    val mapNightTheme: Boolean,
)
