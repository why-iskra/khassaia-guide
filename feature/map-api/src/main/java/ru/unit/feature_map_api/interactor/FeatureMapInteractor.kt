package ru.unit.feature_map_api.interactor

import ru.unit.feature_map_api.models.LocationData
import ru.unit.feature_map_api.models.MoveCameraData
import ru.unit.feature_map_api.models.PlacemarkData

interface FeatureMapInteractor {

    suspend fun zoomIn()

    suspend fun zoomOut()

    suspend fun moveTo(data: MoveCameraData)

    suspend fun addPlacemark(data: PlacemarkData)

    suspend fun removeAllPlacemarks()

    suspend fun location(userLocationData: LocationData?)

    suspend fun startRoute(destination: LocationData)

    suspend fun finishRoute()
}