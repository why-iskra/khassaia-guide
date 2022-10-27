package ru.unit.repository_preferences_api.preferences

import ru.unit.repository_preferences_api.Preferences
import ru.unit.repository_preferences_api.models.SettingsData

interface SettingsPreferences : Preferences<SettingsData> {

    suspend fun updateSyncWithSystemTheme(value: Boolean)
    suspend fun updateNightTheme(value: Boolean)
    suspend fun updateMapSyncWithAppTheme(value: Boolean)
    suspend fun updateMapNightTheme(value: Boolean)

}