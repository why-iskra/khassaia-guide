package ru.unit.feature_main.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unit.feature_main_api.contract.FeatureMainContract
import ru.unit.feature_main_impl.contract.FeatureMainContractImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FeatureMainModule {

    @Singleton
    @Binds
    abstract fun provideFeatureMainContract(
        contract: FeatureMainContractImpl
    ): FeatureMainContract

}