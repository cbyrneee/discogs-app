@file:Suppress("UnstableApiUsage", "unused")

import dev.cbyrne.discogs.buildlogic.android
import dev.cbyrne.discogs.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            android {
                buildFeatures {
                    compose = true
                }

                composeOptions {
                    kotlinCompilerExtensionVersion =
                        libs.findVersion("compose-compiler").get().toString()
                }
            }

            dependencies {
                // AndroidX
                add("implementation", libs.findBundle("androidx.compose").get())

                // Compose
                add("implementation", libs.findBundle("compose").get())
                add("debugImplementation", libs.findBundle("compose.debug").get())

                // Lifecycle
                add("implementation", libs.findBundle("lifecycle").get())
            }
        }
    }
}