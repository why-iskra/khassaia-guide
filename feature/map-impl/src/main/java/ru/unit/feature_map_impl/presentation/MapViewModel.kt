package ru.unit.feature_map_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yandex.mapkit.directions.driving.DrivingRoute
import com.yandex.mapkit.map.CameraPosition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.unit.feature_map_api.models.LocationData
import ru.unit.feature_map_api.models.MoveCameraData
import ru.unit.feature_map_api.models.PlacemarkData
import ru.unit.feature_map_impl.contract.FeatureMapContractImpl
import ru.unit.feature_map_impl.interactor.FeatureMapInteractorImpl
import ru.unit.repository_preferences_api.models.SettingsData
import ru.unit.repository_preferences_api.preferences.SettingsPreferences
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val mapInteractor: FeatureMapInteractorImpl,
    private val mapContract: FeatureMapContractImpl,
    private val settingsPreferences: SettingsPreferences
) : ViewModel() {

    private val _flowCamera = MutableStateFlow<CameraPosition?>(null)
    val flowCamera: StateFlow<CameraPosition?> get() = _flowCamera

    private val _flowDrivingRoute = MutableSharedFlow<DrivingRoute>()
    val flowDrivingRoute: SharedFlow<DrivingRoute> get() = _flowDrivingRoute

    private val _flowCameraMoving = MutableStateFlow(false)

    val flowZoomIn: Flow<Unit> =
        mapInteractor.flowZoomIn.filter { !_flowCameraMoving.value }.onEach { notifyCameraMove() }

    val flowZoomOut: Flow<Unit> =
        mapInteractor.flowZoomOut.filter { !_flowCameraMoving.value }.onEach { notifyCameraMove() }

    val flowMoveTo: Flow<MoveCameraData?> = mapInteractor.flowMoveTo
        .onEach {
            if (it?.animation != null) {
                notifyCameraMove()
            }
        }

    val flowAddPlacemark: Flow<PlacemarkData> = mapInteractor.flowAddPlacemark

    val flowRemoveAllPlacemarks: Flow<Unit> = mapInteractor.flowRemoveAllPlacemarks

    val flowLocation: StateFlow<LocationData?> = mapInteractor.flowLocation

    val flowStartRoute: Flow<LocationData?> = mapInteractor.flowStartRoute

    val flowSettings = settingsPreferences.dataFlow.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        SettingsData(
            syncWithSystemTheme = true,
            nightTheme = false,
            mapSyncWithAppTheme = true,
            mapNightTheme = false
        )
    )

    private fun notifyCameraMove() {
        _flowCameraMoving.value = true
    }

    fun notifyCameraMoveStop() {
        _flowCameraMoving.value = false
    }

    fun notifyPlacemarkTapped(data: PlacemarkData) {
        viewModelScope.launch {
            mapContract.placemarkTapped(data)
        }
    }

    fun notifyDrivingSessionClosed() {
        viewModelScope.launch(Dispatchers.Main) {
            mapContract.routeTime(null)
        }
    }

    fun closeDrivingSession() {
        viewModelScope.launch(Dispatchers.Main) {
            mapInteractor.finishRoute()
            notifyDrivingSessionClosed()
        }
    }

    fun notifyDrivingRoutes(list: MutableList<DrivingRoute>) {
        if (list.isEmpty()) {
            closeDrivingSession()

            return
        }

        viewModelScope.launch(Dispatchers.Main) {
            _flowDrivingRoute.emit(list.first())

            mapContract.routeTime(list.firstOrNull()?.metadata?.weight?.time?.text)
        }
    }

    fun notifyCamera(camera: CameraPosition) {
        viewModelScope.launch(Dispatchers.Main) {
            _flowCamera.emit(camera)
        }
    }
}