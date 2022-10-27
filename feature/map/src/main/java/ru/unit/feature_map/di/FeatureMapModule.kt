package ru.unit.feature_map.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unit.feature_map_api.contract.FeatureMapContract
import ru.unit.feature_map_api.interactor.FeatureMapInteractor
import ru.unit.feature_map_impl.contract.FeatureMapContractImpl
import ru.unit.feature_map_impl.interactor.FeatureMapInteractorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FeatureMapModule {

    @Singleton
    @Binds
    abstract fun provideMapInterator(mapInteractor: FeatureMapInteractorImpl): FeatureMapInteractor

    @Singleton
    @Binds
    abstract fun provideMapContract(mapContract: FeatureMapContractImpl): FeatureMapContract
}