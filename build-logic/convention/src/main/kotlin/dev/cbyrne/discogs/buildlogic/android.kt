package dev.cbyrne.discogs.buildlogic

import com.android.build.api.dsl.CommonExtension

fun configureAndroid(extension: CommonExtension<*, *, *, *>) {
    with(extension) {
        compileSdk = TARGET_SDK

        defaultConfig {
            minSdk = MIN_SDK
        }

        compileOptions {
            sourceCompatibility = TARGET_JAVA_VERSION
            targetCompatibility = TARGET_JAVA_VERSION
        }

        kotlinOptions {
            jvmTarget = TARGET_JAVA_VERSION.toString()
            freeCompilerArgs = listOf(
                "-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
                "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
            )
        }
    }
}