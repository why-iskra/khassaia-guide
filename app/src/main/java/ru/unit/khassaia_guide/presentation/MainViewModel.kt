package ru.unit.khassaia_guide.presentation

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.unit.core_configuration_api.Configuration
import ru.unit.feature_map_api.interactor.FeatureMapInteractor
import ru.unit.feature_map_api.models.PlacemarkData
import ru.unit.feature_map_controller_api.interactor.FeatureMapControllerInteractor
import ru.unit.feature_map_controller_api.models.LocationData
import ru.unit.feature_route_api.interactor.FeatureRouteInteractor
import ru.unit.khassaia_guide.location.LocationListener
import ru.unit.repository_places.mock.PlacesMock
import ru.unit.repository_preferences_api.preferences.SettingsPreferences
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val configuration: Configuration,
    private val featureMapControllerInteractor: FeatureMapControllerInteractor,
    private val featureMapInteractor: FeatureMapInteractor,
    private val featureRouteInteractor: FeatureRouteInteractor,
    private val places: PlacesMock,
    private val locationListener: LocationListener,
    private val settingsPreferences: SettingsPreferences
) : ViewModel() {

    private val _flowStartLocation = MutableStateFlow(false)
    private val _flowLocationEnabled = MutableStateFlow(false)

    private val _flowLocation = _flowStartLocation.combine(_flowLocationEnabled) { start, enable ->
        start to enable
    }

    val flowSettingsPreferences = settingsPreferences.dataFlow.onEach {
        repaintPlacemarks()
    }

    private var isInited = false

    init {
        viewModelScope.launch {
            locationListener.lastLocation.collect {
                if (it != null) {
                    location(it)
                }
            }
        }

        viewModelScope.launch {
            _flowLocation.collect {
                val started = it.first
                val enabled = it.second

                when {
                    enabled && started -> locationListener.startLocationUpdates()
                    enabled && !started -> locationListener.stopLocationUpdates()
                }
            }
        }
    }

    fun init() {
        if (!isInited) {
            isInited = true

            viewModelScope.launch(Dispatchers.Main) {
                delay(500)
                featureMapInteractor.moveTo(configuration.startCameraPosition)
            }
        }

        viewModelScope.launch(Dispatchers.Main) {
            repaintPlacemarks()
        }
    }

    private fun repaintPlacemarks() {
        viewModelScope.launch(Dispatchers.Main) {
            featureMapInteractor.removeAllPlacemarks()

            val list = places.list.map {
                PlacemarkData(
                    id = it.id,
                    lat = it.lat,
                    lon = it.lon
                )
            }

            for (i in list) {
                featureMapInteractor.addPlacemark(i)
            }
        }
    }

    fun enableLocation() {
        viewModelScope.launch(Dispatchers.Main) {
            _flowLocationEnabled.emit(true)
        }
    }

    fun disableLocation() {
        viewModelScope.launch(Dispatchers.Main) {
            _flowLocationEnabled.emit(false)
        }
    }

    fun startLocation() {
        viewModelScope.launch(Dispatchers.Main) {
            _flowStartLocation.emit(true)
        }
    }

    fun stopLocation() {
        viewModelScope.launch(Dispatchers.Main) {
            _flowStartLocation.emit(false)
        }
    }

    private fun location(location: Location) {
        viewModelScope.launch(Dispatchers.Main) {
            featureMapControllerInteractor.location(
                LocationData(
                    location.latitude,
                    location.longitude,
                )
            )

            featureMapInteractor.location(
                ru.unit.feature_map_api.models.LocationData(
                    location.latitude,
                    location.longitude
                )
            )

            featureRouteInteractor.location(
                ru.unit.feature_route_api.models.LocationData(
                    location.latitude,
                    location.longitude
                )
            )
        }
    }
}