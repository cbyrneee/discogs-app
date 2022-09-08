@file:Suppress("UnstableApiUsage", "unused")

import dev.cbyrne.discogs.buildlogic.androidLibrary
import dev.cbyrne.discogs.buildlogic.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            androidLibrary { configureCompose(this) }
        }
    }
}