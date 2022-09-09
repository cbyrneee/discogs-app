package dev.cbyrne.discogs.feature.discover.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.cbyrne.discogs.common.navigation.Route
import dev.cbyrne.discogs.feature.discover.view.HomeView

fun NavGraphBuilder.discoverRoutes(navController: NavController) {
    composable(route = Route.Discover.Home.route) {
        HomeView()
    }
}