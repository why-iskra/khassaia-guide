package ru.unit.repository_preferences_impl

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import ru.unit.repository_preferences_api.models.SettingsData
import ru.unit.repository_preferences_api.preferences.SettingsPreferences
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsPreferencesImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : SettingsPreferences {
    private val Context.dataStore by preferencesDataStore(
        name = this.javaClass.name + ".settings"
    )

    override val dataFlow = context.dataStore.data.catch {
        if (it is IOException) {
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map {
        SettingsData(
            syncWithSystemTheme = it[SYNC_WITH_SYSTEM_THEME] ?: true,
            nightTheme = it[NIGHT_THEME] ?: false,
            mapSyncWithAppTheme = it[MAP_SYNC_WITH_APP_THEME] ?: true,
            mapNightTheme = it[MAP_NIGHT_THEME] ?: false
        )
    }

    override suspend fun update(data: SettingsData) {
        context.dataStore.edit { preferences ->
            preferences[SYNC_WITH_SYSTEM_THEME] = data.syncWithSystemTheme
            preferences[NIGHT_THEME] = data.nightTheme
            preferences[MAP_SYNC_WITH_APP_THEME] = data.mapSyncWithAppTheme
            preferences[MAP_NIGHT_THEME] = data.mapNightTheme
        }
    }

    override suspend fun updateSyncWithSystemTheme(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SYNC_WITH_SYSTEM_THEME] = value
        }
    }

    override suspend fun updateNightTheme(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[NIGHT_THEME] = value
        }
    }

    override suspend fun updateMapSyncWithAppTheme(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[MAP_SYNC_WITH_APP_THEME] = value
        }
    }

    override suspend fun updateMapNightTheme(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[MAP_NIGHT_THEME] = value
        }
    }

    private companion object {
        private val SYNC_WITH_SYSTEM_THEME = booleanPreferencesKey("syncWithSystemTheme")
        private val NIGHT_THEME = booleanPreferencesKey("nightTheme")
        private val MAP_SYNC_WITH_APP_THEME = booleanPreferencesKey("mapSyncWithAppTheme")
        private val MAP_NIGHT_THEME = booleanPreferencesKey("mapNightTheme")
    }
}