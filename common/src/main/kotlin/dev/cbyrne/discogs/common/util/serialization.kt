package dev.cbyrne.discogs.common.util

import kotlinx.serialization.json.Json

val json = Json {
    prettyPrint = true
    ignoreUnknownKeys = true
    isLenient = true
}