package dev.cbyrne.discogs

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
import dev.cbyrne.discogs.ui.view.HomeView

@Composable
fun RootView(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: RootViewModel = hiltViewModel()
) {
    val credentials = viewModel.credentials

    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = if (credentials != null) Route.Home.route else Route.Auth.Login.route
    ) {
        authRoutes(navController)

        composable(Route.Home.route) {
            HomeView()
        }
    }
}