package dev.cbyrne.discogs.app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.cbyrne.discogs.common.navigation.Route
import dev.cbyrne.discogs.feature.auth.navigation.authRoutes
import dev.cbyrne.discogs.ui.view.HomeView

@Composable
fun NavigationHost(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = Route.Auth.Login.route
    ) {
        authRoutes()

        composable(Route.Home.route) {
            HomeView()
        }
    }
}