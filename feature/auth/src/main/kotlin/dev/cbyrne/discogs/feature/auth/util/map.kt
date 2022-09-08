@file:Suppress("UNCHECKED_CAST")

package dev.cbyrne.discogs.feature.auth.util

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class NamedMapDelegateM<T>(
    private val map: Map<String, Any?>,
    private val name: String
) : ReadOnlyProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T = map[name] as T
}

fun <T> Map<String, Any?>.named(name: String): NamedMapDelegateM<T> = NamedMapDelegateM(this, name)
