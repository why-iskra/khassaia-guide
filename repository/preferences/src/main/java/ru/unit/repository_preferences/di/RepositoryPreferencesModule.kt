package ru.unit.repository_preferences.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unit.repository_preferences_api.preferences.SettingsPreferences
import ru.unit.repository_preferences_impl.SettingsPreferencesImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryPreferencesModule {

    @Singleton
    @Binds
    abstract fun provideSettingsPreferences(impl: SettingsPreferencesImpl): SettingsPreferences

}