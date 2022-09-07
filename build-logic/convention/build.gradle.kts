plugins {
    `kotlin-dsl`
}

group = "dev.cbyrne.discogs.buildlogic"

dependencies {
    implementation(libs.bundles.buildlogic)
}

gradlePlugin {
    plugins {
        register("androidApp") {
            id = "discogs.android.app"
            implementationClass = "AndroidAppConventionPlugin"
        }
    }
}