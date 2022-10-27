package ru.unit.feature_map_impl.contract

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import ru.unit.feature_map_api.contract.FeatureMapContract
import ru.unit.feature_map_api.models.PlacemarkData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureMapContractImpl @Inject constructor() : FeatureMapContract {

    private val _flowPlacemarkTapped = MutableSharedFlow<PlacemarkData>()
    private val _flowRouteTime = MutableStateFlow<String?>(null)

    override val flowPlacemarkTapped: SharedFlow<PlacemarkData> get() = _flowPlacemarkTapped
    override val flowRouteTime: StateFlow<String?> get() = _flowRouteTime

    suspend fun placemarkTapped(data: PlacemarkData) {
        _flowPlacemarkTapped.emit(data)
    }

    suspend fun routeTime(time: String?) {
        _flowRouteTime.emit(time)
    }
}