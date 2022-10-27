package ru.unit.core_configuration_impl

import ru.unit.core_configuration_api.Configuration
import ru.unit.feature_map_api.models.MoveCameraData
import javax.inject.Inject

class ConfigurationImpl @Inject constructor() : Configuration {
    override val yandexMapApiKey: String
        get() = BuildConfig.MAPKIT_KEY

    override val startCameraPosition: MoveCameraData
        get() = MoveCameraData(53.721154, 91.042390, 7f)
}