import dev.cbyrne.discogs.buildlogic.TARGET_SDK
import dev.cbyrne.discogs.buildlogic.android
import dev.cbyrne.discogs.buildlogic.configureAndroid
import dev.cbyrne.discogs.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            android {
                configureAndroid(this)

                defaultConfig {
                    targetSdk = TARGET_SDK
                }
            }

            dependencies {
                add("implementation", libs.findBundle("androidx").get())
            }
        }
    }
}
