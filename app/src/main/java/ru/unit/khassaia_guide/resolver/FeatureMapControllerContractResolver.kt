package ru.unit.khassaia_guide.resolver

import androidx.lifecycle.LifecycleCoroutineScope
import ru.unit.core_utils.lifecycle.ktx.collectOnLifecycle
import ru.unit.feature_map_api.interactor.FeatureMapInteractor
import ru.unit.feature_map_api.models.MoveCameraData
import ru.unit.feature_map_controller_api.contract.FeatureMapControllerContract
import ru.unit.khassaia_guide.presentation.MainActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureMapControllerContractResolver @Inject constructor(
    private val contract: FeatureMapControllerContract,
    private val featureMapInteractor: FeatureMapInteractor
) : LifecycleResolver<MainActivity>() {

    override fun MainActivity.onSubscribe(lifecycleScope: LifecycleCoroutineScope) {
        contract.flowMoveTo.collectOnLifecycle(lifecycle) {
            featureMapInteractor.moveTo(MoveCameraData(it.lat, it.lon))
        }
        contract.flowZoomIn.collectOnLifecycle(lifecycle) {
            featureMapInteractor.zoomIn()
        }
        contract.flowZoomOut.collectOnLifecycle(lifecycle) {
            featureMapInteractor.zoomOut()
        }
    }
}