package dev.cbyrne.discogs.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import dev.cbyrne.discogs.common.navigation.Route
import dev.cbyrne.discogs.feature.auth.CallbackScreen
import dev.cbyrne.discogs.feature.auth.LoginScreen

fun NavGraphBuilder.authRoutes(navController: NavHostController) {
    composable(Route.Auth.Login.route) {
        LoginScreen()
    }

    composable(
        route = Route.Auth.Callback.route,
        deepLinks = listOf(navDeepLink {
            uriPattern = "discogs://callback?oauth_token={oauth_token}"
        })
    ) {
        CallbackScreen(
            { navController.navigate(Route.Auth.Login.route) },
            it.arguments?.getString("oauth_token")
        )
    }
}