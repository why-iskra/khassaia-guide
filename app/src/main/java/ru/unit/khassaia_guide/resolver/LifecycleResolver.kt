package ru.unit.khassaia_guide.resolver

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope

abstract class LifecycleResolver<T : LifecycleOwner> {

    fun subscribe(lifecycleOwner: T) {
        lifecycleOwner.onSubscribe(lifecycleOwner.lifecycleScope)
    }

    abstract fun T.onSubscribe(lifecycleScope: LifecycleCoroutineScope)
}