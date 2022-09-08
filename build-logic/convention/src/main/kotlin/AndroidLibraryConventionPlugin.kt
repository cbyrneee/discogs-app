@file:Suppress("unused")

import dev.cbyrne.discogs.buildlogic.androidLibrary
import dev.cbyrne.discogs.buildlogic.configureAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            androidLibrary {
                configureAndroid(this)
            }
        }
    }
}