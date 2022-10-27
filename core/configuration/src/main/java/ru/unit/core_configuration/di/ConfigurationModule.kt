package ru.unit.core_configuration.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unit.core_configuration_api.Configuration
import ru.unit.core_configuration_impl.ConfigurationImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConfigurationModule {

    @Singleton
    @Binds
    abstract fun provideConfiguration(configuration: ConfigurationImpl): Configuration
}