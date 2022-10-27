package ru.unit.khassaia_guide.resolver

import androidx.lifecycle.LifecycleCoroutineScope
import ru.unit.core_utils.lifecycle.ktx.collectOnLifecycle
import ru.unit.feature_map_api.interactor.FeatureMapInteractor
import ru.unit.feature_map_api.models.LocationData
import ru.unit.feature_map_api.models.MoveCameraData
import ru.unit.feature_route_api.contract.FeatureRouteContract
import ru.unit.khassaia_guide.presentation.MainActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureRouteContractResolver @Inject constructor(
    private val contract: FeatureRouteContract,
    private val mapInteractor: FeatureMapInteractor
) : LifecycleResolver<MainActivity>() {

    override fun MainActivity.onSubscribe(lifecycleScope: LifecycleCoroutineScope) {
        contract.flowMoveTo.collectOnLifecycle(lifecycle) {
            mapInteractor.moveTo(
                MoveCameraData(
                    it.lat,
                    it.lon,
                    it.zoom
                )
            )
        }
        contract.flowRoute.collectOnLifecycle(lifecycle) {
            if (it == null) {
                mapInteractor.finishRoute()
            } else {
                val data = LocationData(
                    it.lat,
                    it.lon
                )

                mapInteractor.startRoute(data)
            }
        }
    }
}