package ru.unit.feature_main_impl.contract

import android.view.MotionEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import ru.unit.feature_main_api.contract.FeatureMainContract
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureMainContractImpl @Inject constructor() : FeatureMainContract {

    private val _flowNavigateToRoute = MutableSharedFlow<Int>()
    override val flowNavigateToRoute: SharedFlow<Int> get() = _flowNavigateToRoute

    private val _flowSendTouchEventToMap = MutableSharedFlow<MotionEvent>()
    override val flowSendTouchEventToMap: SharedFlow<MotionEvent> get() = _flowSendTouchEventToMap

    suspend fun navigateToRoute(id: Int) {
        _flowNavigateToRoute.emit(id)
    }

    suspend fun sendTouchEventToMap(motionEvent: MotionEvent) {
        _flowSendTouchEventToMap.emit(motionEvent)
    }
}