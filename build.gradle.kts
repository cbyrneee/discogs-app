// NOTE: Keep this suppression until https://youtrack.jetbrains.com/issue/KTIJ-19369 is closed
@file:Suppress("DSL_SCOPE_VIOLATION")

buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.43.2")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
}
