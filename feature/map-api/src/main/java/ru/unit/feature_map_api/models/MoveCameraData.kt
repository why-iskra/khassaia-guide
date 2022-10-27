package ru.unit.feature_map_api.models

data class MoveCameraData(
    val lat: Double,
    val lon: Double,
    val zoom: Float? = null,
    val animation: Float? = 0.5f
)
