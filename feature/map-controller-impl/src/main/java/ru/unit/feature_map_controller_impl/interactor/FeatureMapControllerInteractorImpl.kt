package ru.unit.feature_map_controller_impl.interactor

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.unit.feature_map_controller_api.interactor.FeatureMapControllerInteractor
import ru.unit.feature_map_controller_api.models.LocationData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureMapControllerInteractorImpl @Inject constructor() : FeatureMapControllerInteractor {

    private val _flowLocation = MutableStateFlow<LocationData?>(null)
    val flowLocation: StateFlow<LocationData?> get() = _flowLocation

    override suspend fun location(locationData: LocationData?) {
        _flowLocation.emit(locationData)
    }
}