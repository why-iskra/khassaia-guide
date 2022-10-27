package ru.unit.feature_map_impl.interactor

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import ru.unit.feature_map_api.interactor.FeatureMapInteractor
import ru.unit.feature_map_api.models.LocationData
import ru.unit.feature_map_api.models.MoveCameraData
import ru.unit.feature_map_api.models.PlacemarkData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureMapInteractorImpl @Inject constructor() : FeatureMapInteractor {

    private val _flowZoomIn = MutableSharedFlow<Unit>()
    private val _flowZoomOut = MutableSharedFlow<Unit>()
    private val _flowMoveTo = MutableSharedFlow<MoveCameraData>()
    private val _flowAddPlacemark = MutableSharedFlow<PlacemarkData>()
    private val _flowRemoveAllPlacemarks = MutableSharedFlow<Unit>()
    private val _flowLocation = MutableStateFlow<LocationData?>(null)
    private val _flowStartRoute = MutableSharedFlow<LocationData?>()

    val flowZoomIn: SharedFlow<Unit> get() = _flowZoomIn
    val flowZoomOut: SharedFlow<Unit> get() = _flowZoomOut
    val flowMoveTo: SharedFlow<MoveCameraData> get() = _flowMoveTo
    val flowAddPlacemark: SharedFlow<PlacemarkData> get() = _flowAddPlacemark
    val flowRemoveAllPlacemarks: SharedFlow<Unit> get() = _flowRemoveAllPlacemarks
    val flowLocation: StateFlow<LocationData?> get() = _flowLocation
    val flowStartRoute: SharedFlow<LocationData?> get() = _flowStartRoute

    override suspend fun zoomIn() {
        _flowZoomIn.emit(Unit)
    }

    override suspend fun zoomOut() {
        _flowZoomOut.emit(Unit)
    }

    override suspend fun moveTo(data: MoveCameraData) {
        _flowMoveTo.emit(data)
    }

    override suspend fun addPlacemark(data: PlacemarkData) {
        _flowAddPlacemark.emit(data)
    }

    override suspend fun removeAllPlacemarks() {
        _flowRemoveAllPlacemarks.emit(Unit)
    }

    override suspend fun location(userLocationData: LocationData?) {
        _flowLocation.emit(userLocationData)
    }

    override suspend fun startRoute(destination: LocationData) {
        _flowStartRoute.emit(destination)
    }

    override suspend fun finishRoute() {
        _flowStartRoute.emit(null)
    }
}