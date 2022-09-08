package dev.cbyrne.discogs.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("UnstableApiUsage")
fun Project.configureCompose(extension: CommonExtension<*, *, *, *>) {
    with(extension) {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion =
                libs.findVersion("compose-compiler").get().toString()
        }
    }

    dependencies {
        // Accompanist
        add("implementation", libs.findBundle("accompanist").get())

        // AndroidX
        add("implementation", libs.findBundle("androidx.compose").get())

        // Compose
        add("implementation", libs.findBundle("compose").get())
        add("debugImplementation", libs.findBundle("compose.debug").get())

        // Lifecycle
        add("implementation", libs.findBundle("lifecycle").get())
    }
}