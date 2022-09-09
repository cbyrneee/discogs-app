package dev.cbyrne.discogs.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import dev.cbyrne.discogs.common.navigation.Route
import dev.cbyrne.discogs.feature.auth.view.CallbackScreen
import dev.cbyrne.discogs.feature.auth.view.LoginScreen

fun NavGraphBuilder.authRoutes(navController: NavHostController) {
    composable(
        route = Route.Auth.Login.route,
        deepLinks = listOf(navDeepLink {
            uriPattern = "discogs://callback?denied={denied}"
        })
    ) {
        LoginScreen()
    }

    composable(
        route = Route.Auth.Callback.route,
        deepLinks = listOf(navDeepLink {
            uriPattern =
                "discogs://callback?oauth_token={oauth_token}&oauth_verifier={oauth_verifier}"
        })
    ) { backStackEntry ->
        CallbackScreen(
            navigateTo = { navController.navigate(it.route) },
            backStackEntry.arguments?.getString("oauth_token"),
            backStackEntry.arguments?.getString("oauth_verifier")
        )
    }
}