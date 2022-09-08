import dev.cbyrne.discogs.buildlogic.*
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class AndroidAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            android {
                compileSdk = TARGET_SDK

                defaultConfig {
                    minSdk = MIN_SDK
                    targetSdk = TARGET_SDK
                }

                compileOptions {
                    sourceCompatibility = TARGET_JAVA_VERSION
                    targetCompatibility = TARGET_JAVA_VERSION
                }

                kotlinOptions {
                    jvmTarget = TARGET_JAVA_VERSION.toString()
                    freeCompilerArgs = listOf(
                        "-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
                        "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
                    )
                }
            }
        }
    }
}
