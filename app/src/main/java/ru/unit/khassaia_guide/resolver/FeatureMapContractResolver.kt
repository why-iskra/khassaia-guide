package ru.unit.khassaia_guide.resolver

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.findNavController
import ru.unit.core_utils.lifecycle.ktx.collectOnLifecycle
import ru.unit.feature_map_api.contract.FeatureMapContract
import ru.unit.feature_route_api.interactor.FeatureRouteInteractor
import ru.unit.khassaia_guide.NavGraphDirections
import ru.unit.khassaia_guide.R
import ru.unit.khassaia_guide.presentation.MainActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureMapContractResolver @Inject constructor(
    private val contract: FeatureMapContract,
    private val routeInteractor: FeatureRouteInteractor,
) : LifecycleResolver<MainActivity>() {

    override fun MainActivity.onSubscribe(lifecycleScope: LifecycleCoroutineScope) {
        contract.flowPlacemarkTapped.collectOnLifecycle(lifecycle) {
            findNavController(R.id.navigation).navigate(
                NavGraphDirections.actionGlobalNavGraphRoute(it.id)
            )
        }
        contract.flowRouteTime.collectOnLifecycle(lifecycle) {
            routeInteractor.routeTime(it)
        }
    }
}