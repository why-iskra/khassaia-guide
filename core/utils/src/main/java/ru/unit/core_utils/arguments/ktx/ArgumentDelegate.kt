package ru.unit.core_utils.arguments.ktx

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlin.reflect.KProperty

interface LazyProvider<A, T> {
    operator fun provideDelegate(thisRef: A, prop: KProperty<*>): Lazy<T>
}

inline fun <reified T> Fragment.argumentDelegate(): LazyProvider<Fragment, T> {
    return argumentDelegate { it.requireArguments() }
}

inline fun <F, reified T> argumentDelegate(crossinline provideArguments: (F) -> Bundle?):
        LazyProvider<F, T> = object : LazyProvider<F, T> {
    override fun provideDelegate(thisRef: F, prop: KProperty<*>): Lazy<T> = lazy {
        val bundle = provideArguments(thisRef)
        bundle?.get(prop.name) as T
    }
}