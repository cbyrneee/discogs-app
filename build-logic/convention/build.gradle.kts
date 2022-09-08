plugins {
    `kotlin-dsl`
}

group = "dev.cbyrne.discogs.buildlogic"

dependencies {
    implementation(libs.bundles.buildlogic)
}

gradlePlugin {
    plugins {
        // Configures the build for an android application
        register("androidApp") {
            id = "discogs.android.app"
            implementationClass = "AndroidAppConventionPlugin"
        }

        // Adds any common dependencies (i.e. Hilt, OkHttp, etc)
        register("common") {
            id = "discogs.common"
            implementationClass = "CommonConventionPlugin"
        }

        // Configures the build for working with compose and adds any related dependencies
        register("compose") {
            id = "discogs.compose"
            implementationClass = "ComposeConventionPlugin"
        }
    }
}