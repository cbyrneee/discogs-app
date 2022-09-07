import kotlinx.serialization.json.Json

internal val json = Json {
    prettyPrint = true
    ignoreUnknownKeys = true
    isLenient = true
}