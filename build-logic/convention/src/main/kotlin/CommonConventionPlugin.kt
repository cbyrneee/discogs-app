@file:Suppress("unused")

import dev.cbyrne.discogs.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class CommonConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.dagger.hilt.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("kotlin-kapt")
            }

            dependencies {
                // Hilt
                add("implementation", libs.findBundle("hilt").get())
                add("kapt", libs.findLibrary("hilt.compiler").get())

                // KotlinX
                add("implementation", libs.findBundle("kotlinx.serialization").get())

                // OkHttp
                add("implementation", libs.findBundle("okhttp").get())

                // Retrofit
                add("implementation", libs.findBundle("retrofit").get())
            }
        }
    }
}