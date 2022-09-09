package dev.cbyrne.discogs.ui.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import dev.cbyrne.discogs.common.navigation.Route
import dev.cbyrne.discogs.navigation.ROUTES
import androidx.compose.material3.NavigationBar as Material3NavigationBar

@Composable
fun DiscogsNavigationBar(navController: NavController, currentScreen: Route?) {
    Material3NavigationBar {
        ROUTES
            .filter { it.isVisible }
            .forEach { screen ->
                ScreenNavigationItem(
                    route = screen,
                    isSelected = currentScreen == screen,
                    navController = navController
                )
            }
    }
}

@Composable
fun RowScope.ScreenNavigationItem(
    route: Route,
    isSelected: Boolean,
    navController: NavController
) {
    NavigationBarItem(
        icon = { Icon(imageVector = route.icon, contentDescription = route.name) },
        label = { Text(text = route.name) },
        selected = isSelected,
        onClick = {
            navController.navigate(route.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }

                launchSingleTop = true
                restoreState = true
            }
        }
    )
}
