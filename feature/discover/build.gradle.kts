plugins {
    id("discogs.android.library")
    id("discogs.android.library.compose")
    id("discogs.common")
}

dependencies {
    implementation(project(":common"))
    implementation(project(":ui"))
}