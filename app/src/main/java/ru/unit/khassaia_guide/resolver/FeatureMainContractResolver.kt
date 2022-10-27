package ru.unit.khassaia_guide.resolver

import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.findNavController
import ru.unit.core_utils.lifecycle.ktx.collectOnLifecycle
import ru.unit.feature_main_api.contract.FeatureMainContract
import ru.unit.khassaia_guide.NavGraphDirections
import ru.unit.khassaia_guide.R
import ru.unit.khassaia_guide.presentation.MainActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureMainContractResolver @Inject constructor(
    private val contract: FeatureMainContract
) : LifecycleResolver<MainActivity>() {

    override fun MainActivity.onSubscribe(lifecycleScope: LifecycleCoroutineScope) {
        contract.flowNavigateToRoute.collectOnLifecycle(lifecycle) {
            findNavController(R.id.navigation).navigate(
                NavGraphDirections.actionGlobalNavGraphRoute(it)
            )
        }
        contract.flowSendTouchEventToMap.collectOnLifecycle(lifecycle) {
            findViewById<FragmentContainerView>(ru.unit.feature_map_api.R.id.mapFragmentContainerView).dispatchTouchEvent(
                it
            )
        }
    }
}