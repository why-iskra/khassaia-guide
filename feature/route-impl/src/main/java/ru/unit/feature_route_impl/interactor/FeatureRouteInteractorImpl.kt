package ru.unit.feature_route_impl.interactor

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import ru.unit.feature_route_api.interactor.FeatureRouteInteractor
import ru.unit.feature_route_api.models.LocationData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureRouteInteractorImpl @Inject constructor() : FeatureRouteInteractor {

    private val _flowRouteTime = MutableSharedFlow<String?>()
    private val _flowLocation = MutableStateFlow<LocationData?>(null)

    val flowRouteTime: SharedFlow<String?> get() = _flowRouteTime
    val flowLocation: StateFlow<LocationData?> get() = _flowLocation

    override suspend fun routeTime(time: String?) {
        _flowRouteTime.emit(time)
    }

    override suspend fun location(location: LocationData?) {
        _flowLocation.emit(location)
    }
}