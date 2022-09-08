package dev.cbyrne.discogs.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.cbyrne.discogs.common.navigation.Route
import dev.cbyrne.discogs.feature.auth.LoginScreen

fun NavGraphBuilder.authRoutes() {
    composable(Route.Auth.Login.route) {
        LoginScreen()
    }
}