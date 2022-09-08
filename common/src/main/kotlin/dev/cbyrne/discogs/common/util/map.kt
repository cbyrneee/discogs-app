@file:Suppress("UNCHECKED_CAST")

package dev.cbyrne.discogs.common.util

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class NamedMapDelegateM<T>(
    private val map: Map<String, Any?>,
    private val name: String,
    private val transform: (Any?) -> T
) : ReadOnlyProperty<Any, T> {
    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Any, property: KProperty<*>): T = transform(map[name])
}

@Suppress("UNCHECKED_CAST")
fun <T> Map<String, Any?>.named(
    name: String,
    transform: (Any?) -> T = { it as T }
): NamedMapDelegateM<T> =
    NamedMapDelegateM(this, name, transform)