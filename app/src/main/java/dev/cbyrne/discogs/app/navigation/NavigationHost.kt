package dev.cbyrne.discogs.app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.cbyrne.discogs.feature.auth.LoginScreen
import dev.cbyrne.discogs.ui.view.HomeView

@Composable
fun NavigationHost(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navigateToHome = { navController.navigate(Screen.Home.route) })
        }

        composable(Screen.Home.route) {
            HomeView()
        }
    }
}