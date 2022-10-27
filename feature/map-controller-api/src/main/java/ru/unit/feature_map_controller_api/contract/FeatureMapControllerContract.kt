package ru.unit.feature_map_controller_api.contract

import kotlinx.coroutines.flow.SharedFlow
import ru.unit.feature_map_controller_api.models.LocationData

interface FeatureMapControllerContract {

    val flowMoveTo: SharedFlow<LocationData>
    val flowZoomIn: SharedFlow<Unit>
    val flowZoomOut: SharedFlow<Unit>

}