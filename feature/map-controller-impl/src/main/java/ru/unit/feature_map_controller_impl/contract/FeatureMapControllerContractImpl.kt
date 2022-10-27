package ru.unit.feature_map_controller_impl.contract

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import ru.unit.feature_map_controller_api.contract.FeatureMapControllerContract
import ru.unit.feature_map_controller_api.models.LocationData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureMapControllerContractImpl @Inject constructor() : FeatureMapControllerContract {

    private val _flowZoomIn = MutableSharedFlow<Unit>()
    private val _flowZoomOut = MutableSharedFlow<Unit>()
    private val _flowMoveTo = MutableSharedFlow<LocationData>()

    override val flowMoveTo: SharedFlow<LocationData> get() = _flowMoveTo
    override val flowZoomIn: SharedFlow<Unit> get() = _flowZoomIn
    override val flowZoomOut: SharedFlow<Unit> get() = _flowZoomOut

    suspend fun moveTo(locationData: LocationData) {
        _flowMoveTo.emit(locationData)
    }

    suspend fun zoomIn() {
        _flowZoomIn.emit(Unit)
    }

    suspend fun zoomOut() {
        _flowZoomOut.emit(Unit)
    }
}