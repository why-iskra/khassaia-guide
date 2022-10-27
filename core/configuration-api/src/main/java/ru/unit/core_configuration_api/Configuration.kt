package ru.unit.core_configuration_api

import ru.unit.feature_map_api.models.MoveCameraData

interface Configuration {

    val yandexMapApiKey: String

    val startCameraPosition: MoveCameraData

}