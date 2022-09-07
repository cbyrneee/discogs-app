@file:Suppress("UnstableApiUsage")

plugins {
    id("discogs.android.app")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization")

    kotlin("kapt")
}

android {
    namespace = "dev.cbyrne.discogs"

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.bundles.androidx)
    implementation(libs.bundles.androidx.compose)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.hilt)
    implementation(libs.bundles.kotlinx.serialization)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.retrofit)

    debugImplementation(libs.bundles.compose.debug)

    kapt(libs.hilt.compiler)
}