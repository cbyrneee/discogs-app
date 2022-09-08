@file:Suppress("UnstableApiUsage", "unused")

import dev.cbyrne.discogs.buildlogic.android
import dev.cbyrne.discogs.buildlogic.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidAppComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            android { configureCompose(this) }
        }
    }
}