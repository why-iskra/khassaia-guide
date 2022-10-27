package ru.unit.feature_route_impl.contract

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import ru.unit.feature_route_api.contract.FeatureRouteContract
import ru.unit.feature_route_api.models.LocationData
import ru.unit.feature_route_api.models.MoveCameraData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureRouteContractImpl @Inject constructor() : FeatureRouteContract {

    private val _flowMoveTo = MutableSharedFlow<MoveCameraData>()
    private val _flowStartRoute = MutableStateFlow<LocationData?>(null)

    override val flowMoveTo: SharedFlow<MoveCameraData> get() = _flowMoveTo
    override val flowRoute: StateFlow<LocationData?> get() = _flowStartRoute

    suspend fun moveTo(data: MoveCameraData) {
        _flowMoveTo.emit(data)
    }

    suspend fun startRoute(data: LocationData) {
        _flowStartRoute.emit(data)
    }

    suspend fun finishRoute() {
        _flowStartRoute.emit(null)
    }
}