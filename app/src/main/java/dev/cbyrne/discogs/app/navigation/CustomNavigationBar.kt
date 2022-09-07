package dev.cbyrne.discogs.app.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.compose.material3.NavigationBar as Material3NavigationBar

@Composable
fun CustomNavigationBar(navController: NavController, currentScreen: Screen?) {
    Material3NavigationBar {
        SCREENS
            .filter { it.isVisible }
            .forEach { screen ->
                ScreenNavigationItem(
                    screen = screen,
                    isSelected = currentScreen == screen,
                    navController = navController
                )
            }
    }
}

@Composable
fun RowScope.ScreenNavigationItem(
    screen: Screen,
    isSelected: Boolean,
    navController: NavController
) {
    NavigationBarItem(
        icon = { Icon(imageVector = screen.icon, contentDescription = screen.name) },
        label = { Text(text = screen.name) },
        selected = isSelected,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }

                launchSingleTop = true
                restoreState = true
            }
        }
    )
}
