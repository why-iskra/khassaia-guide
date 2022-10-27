package ru.unit.repository_preferences_api

import kotlinx.coroutines.flow.Flow

interface Preferences<T> {

    val dataFlow: Flow<T>

    suspend fun update(data: T)

}