package dev.cbyrne.discogs.app.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material3.NavigationBar as Material3NavigationBar

@Composable
fun CustomNavigationBar(navController: NavController) {
    Material3NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val route = navBackStackEntry?.destination?.route

        SCREENS.forEach { screen ->
            ScreenNavigationItem(
                screen = screen,
                isSelected = route == screen.route,
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
