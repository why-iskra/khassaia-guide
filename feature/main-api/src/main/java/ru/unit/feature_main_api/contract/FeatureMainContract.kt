package ru.unit.feature_main_api.contract

import android.view.MotionEvent
import kotlinx.coroutines.flow.SharedFlow

interface FeatureMainContract {

    val flowNavigateToRoute: SharedFlow<Int>

    val flowSendTouchEventToMap: SharedFlow<MotionEvent>

}