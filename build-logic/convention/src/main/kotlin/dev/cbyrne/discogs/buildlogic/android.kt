package dev.cbyrne.discogs.buildlogic

import com.android.build.api.dsl.BaseFlavor
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import java.util.*

fun Project.configureAndroid(extension: CommonExtension<*, *, *, *>) {
    with(extension) {
        compileSdk = TARGET_SDK

        defaultConfig {
            minSdk = MIN_SDK

            buildConfigFieldFromProperty(project.properties, "consumerKey")
            buildConfigFieldFromProperty(project.properties, "consumerSecret")
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

fun BaseFlavor.buildConfigFieldFromProperty(properties: Map<*, *>, name: String) {
    val propertyValue = properties["discogs.$name"] as? String
        ?: error("Gradle property discogs.$name is null")

    buildConfigField("String", name.toUpperCase(Locale.ROOT), "\"$propertyValue\"")
}
