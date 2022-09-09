package dev.cbyrne.discogs.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.cbyrne.discogs.common.navigation.Route
import dev.cbyrne.discogs.feature.auth.navigation.authRoutes
import dev.cbyrne.discogs.feature.discover.navigation.discoverRoutes
import dev.cbyrne.discogs.ui.view.SettingsView
import dev.cbyrne.discogs.ui.view.model.RootViewModel

@Composable
fun DiscogsNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: RootViewModel = hiltViewModel()
) {
    val credentials = viewModel.credentials

    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = if (credentials != null) Route.Discover.Home.route else Route.Auth.Login.route
    ) {
        authRoutes(navController)
        discoverRoutes(navController)

        composable(Route.Settings.route) {
            SettingsView(navigateTo = { navController.navigate(it.route) })
        }
    }
}