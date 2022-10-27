package ru.unit.feature_map_controller_api.interactor

import ru.unit.feature_map_controller_api.models.LocationData

interface FeatureMapControllerInteractor {

    suspend fun location(locationData: LocationData?)

}