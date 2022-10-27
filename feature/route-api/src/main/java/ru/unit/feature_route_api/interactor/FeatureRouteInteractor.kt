package ru.unit.feature_route_api.interactor

import ru.unit.feature_route_api.models.LocationData

interface FeatureRouteInteractor {

    suspend fun routeTime(time: String?)

    suspend fun location(location: LocationData?)

}