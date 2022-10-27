package ru.unit.feature_route.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unit.feature_route_api.contract.FeatureRouteContract
import ru.unit.feature_route_api.interactor.FeatureRouteInteractor
import ru.unit.feature_route_impl.contract.FeatureRouteContractImpl
import ru.unit.feature_route_impl.interactor.FeatureRouteInteractorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FeatureRouteModule {

    @Singleton
    @Binds
    abstract fun provideRouteContract(contract: FeatureRouteContractImpl): FeatureRouteContract

    @Singleton
    @Binds
    abstract fun provideRouteInteractor(interactor: FeatureRouteInteractorImpl): FeatureRouteInteractor
}