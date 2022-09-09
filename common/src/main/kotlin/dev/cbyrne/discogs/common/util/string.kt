package dev.cbyrne.discogs.common.util

private val humps = "(?<=.)(?=\\p{Upper})".toRegex()
fun String.toSnakeCase() = replace(humps, "_").lowercase()
