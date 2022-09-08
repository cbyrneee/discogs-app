@file:Suppress("UnstableApiUsage")

plugins {
    id("discogs.android.app")
    id("discogs.android.app.compose")
    id("discogs.common")
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

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":feature:auth"))
}