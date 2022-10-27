package ru.unit.feature_route_api.models

data class MoveCameraData(
    val lat: Double,
    val lon: Double,
    val zoom: Float? = null
)
