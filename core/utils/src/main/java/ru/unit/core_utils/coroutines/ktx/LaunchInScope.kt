package ru.unit.core_utils.coroutines.ktx

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun launchInScope(context: CoroutineContext, block: suspend CoroutineScope.() -> Unit) {
    CoroutineScope(context).launch(block = block)
}