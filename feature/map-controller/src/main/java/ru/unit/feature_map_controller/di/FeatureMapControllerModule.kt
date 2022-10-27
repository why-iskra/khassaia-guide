package ru.unit.feature_map_controller.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unit.feature_map_controller_api.contract.FeatureMapControllerContract
import ru.unit.feature_map_controller_api.interactor.FeatureMapControllerInteractor
import ru.unit.feature_map_controller_impl.contract.FeatureMapControllerContractImpl
import ru.unit.feature_map_controller_impl.interactor.FeatureMapControllerInteractorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FeatureMapControllerModule {

    @Singleton
    @Binds
    abstract fun provideFeatureMapControllerContract(
        contract: FeatureMapControllerContractImpl
    ): FeatureMapControllerContract

    @Singleton
    @Binds
    abstract fun provideFeatureMapControllerInteractor(
        interactor: FeatureMapControllerInteractorImpl
    ): FeatureMapControllerInteractor
}