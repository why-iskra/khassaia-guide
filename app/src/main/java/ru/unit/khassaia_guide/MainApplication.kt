package ru.unit.khassaia_guide

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {

    @Inject
    lateinit var configuration: ru.unit.core_configuration_api.Configuration

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        MapKitFactory.setApiKey(configuration.yandexMapApiKey)
    }
}