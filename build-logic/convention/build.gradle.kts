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

        // Configures the application for working with compose and adds any related dependencies
        register("appCompose") {
            id = "discogs.android.app.compose"
            implementationClass = "AndroidAppComposeConventionPlugin"
        }

        // Configures the build for a library project
        register("androidLibrary") {
            id = "discogs.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("libraryCompose") {
            id = "discogs.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        // Adds any common dependencies (i.e. Hilt, OkHttp, etc)
        register("common") {
            id = "discogs.common"
            implementationClass = "CommonConventionPlugin"
        }
    }
}