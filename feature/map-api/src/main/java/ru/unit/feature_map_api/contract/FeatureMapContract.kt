package ru.unit.feature_map_api.contract

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import ru.unit.feature_map_api.models.PlacemarkData

interface FeatureMapContract {

    val flowPlacemarkTapped: SharedFlow<PlacemarkData>

    val flowRouteTime: StateFlow<String?>

}