package ru.unit.feature_settings_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.unit.repository_preferences_api.preferences.SettingsPreferences
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsPreferences: SettingsPreferences
) : ViewModel() {

//    private val _flowSyncWithSystemTheme = MutableStateFlow(false)
//    val flowSyncWithSystemTheme: StateFlow<Boolean> get() = _flowSyncWithSystemTheme
//
//    private val _flowNightMode = MutableStateFlow(false)
//    val flowNightMode: StateFlow<Boolean> get() = _flowNightMode
//
//    private val _flowSyncMapWithAppTheme = MutableStateFlow(false)
//    val flowSyncMapWithAppTheme: StateFlow<Boolean> get() = _flowSyncMapWithAppTheme
//
//    private val _flowMapNightMode = MutableStateFlow(false)
//    val flowMapNightMode: StateFlow<Boolean> get() = _flowMapNightMode

    private val flowSettings = settingsPreferences.dataFlow

    val flowSyncWithSystemTheme: Flow<Boolean> get() = flowSettings.map { it.syncWithSystemTheme }
    val flowNightMode: Flow<Boolean> get() = flowSettings.map { it.nightTheme }
    val flowSyncMapWithAppTheme: Flow<Boolean> get() = flowSettings.map { it.mapSyncWithAppTheme }
    val flowMapNightMode: Flow<Boolean> get() = flowSettings.map { it.mapNightTheme }

    val flowNightModeEnabled: Flow<Boolean> get() = flowSyncWithSystemTheme.map { !it }
    val flowMapNightModeEnabled: Flow<Boolean> get() = flowSyncMapWithAppTheme.map { !it }

    fun notifyCheckedSyncWithSystemTheme(checked: Boolean) {
        viewModelScope.launch(Dispatchers.Main) {
            settingsPreferences.updateSyncWithSystemTheme(checked)
        }
    }

    fun notifyCheckedNightMode(checked: Boolean) {
        viewModelScope.launch(Dispatchers.Main) {
            settingsPreferences.updateNightTheme(checked)
        }
    }

    fun notifyCheckedSyncMapWithAppTheme(checked: Boolean) {
        viewModelScope.launch(Dispatchers.Main) {
            settingsPreferences.updateMapSyncWithAppTheme(checked)
        }
    }

    fun notifyCheckedMapNightMode(checked: Boolean) {
        viewModelScope.launch(Dispatchers.Main) {
            settingsPreferences.updateMapNightTheme(checked)
        }
    }
}