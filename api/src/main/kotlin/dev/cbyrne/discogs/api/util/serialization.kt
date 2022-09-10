package dev.cbyrne.discogs.api.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class PropertiesDecoder<T>(private val wrap: (entries: Map<String, String>) -> T) : KSerializer<T> {
    override val descriptor = PrimitiveSerialDescriptor("PropertyBased", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): T {
        val string = decoder.decodeString()
        val entries = string.split("&")
            .associate {
                val (key, value) = it.split("=")
                key to value
            }

        return wrap(entries)
    }

    override fun serialize(encoder: Encoder, value: T) = throw NotImplementedError()
}
