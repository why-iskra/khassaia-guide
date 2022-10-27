package ru.unit.feature_route_api.contract

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import ru.unit.feature_route_api.models.LocationData
import ru.unit.feature_route_api.models.MoveCameraData

interface FeatureRouteContract {

    val flowMoveTo: SharedFlow<MoveCameraData>
    val flowRoute: StateFlow<LocationData?>

}