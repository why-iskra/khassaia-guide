package ru.unit.core_utils.lifecycle.ktx

import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

fun <T> Flow<T>.collectOnLifecycleOwner(
    viewLifecycleOwner: LifecycleOwner,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    flowCollector: FlowCollector<T>
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(lifecycleState) {
            collect(flowCollector)
        }
    }
}

fun <T> Flow<T>.collectOnLifecycle(
    lifecycle: Lifecycle,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    flowCollector: FlowCollector<T>
) {
    lifecycle.coroutineScope.launch {
        lifecycle.repeatOnLifecycle(lifecycleState) {
            collect(flowCollector)
        }
    }
}
