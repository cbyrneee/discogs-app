package dev.cbyrne.discogs.ui

import android.view.Window
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.cbyrne.discogs.navigation.currentRouteFromBackStack
import dev.cbyrne.discogs.theme.DiscogsTheme
import dev.cbyrne.discogs.ui.navigation.DiscogsNavHost
import dev.cbyrne.discogs.ui.navigation.DiscogsNavigationBar
import dev.cbyrne.discogs.ui.util.RemoveStatusBar

@Composable
fun DiscogsApp(window: Window) {
    DiscogsTheme {
        val navController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentScreen = currentRouteFromBackStack(backStackEntry)

        RemoveStatusBar(window)

        Scaffold(
            bottomBar = {
                if (currentScreen?.hidesNavigationBar == false) {
                    DiscogsNavigationBar(
                        navController = navController,
                        currentScreen = currentScreen
                    )
                }
            }
        ) {
            DiscogsNavHost(
                navController = navController,
                paddingValues = it
            )
        }
    }
}