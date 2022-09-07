package dev.cbyrne.discogs.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry

sealed class Screen(
    val route: String,
    val name: String,
    val icon: ImageVector,
    val isVisible: Boolean = true,
    val hidesNavigationBar: Boolean = false
) {
    object Home : Screen(
        route = "home",
        name = "Home",
        icon = Icons.Filled.Home,
    )
}

internal val SCREENS = setOf(Screen.Home)

internal fun currentScreenFromBackStack(backStackEntry: NavBackStackEntry?): Screen? {
    val route = backStackEntry?.destination?.route
    return SCREENS.firstOrNull { it.route == route }
}