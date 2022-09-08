package dev.cbyrne.discogs.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry

sealed class Route(
    val route: String,
    val name: String,
    val icon: ImageVector,
    val isVisible: Boolean = true,
    val hidesNavigationBar: Boolean = false
) {
    object Login : Route(
        route = "login",
        name = "Login",
        icon = Icons.Filled.Lock,
        isVisible = false,
        hidesNavigationBar = true
    )

    object Home : Route(
        route = "home",
        name = "Home",
        icon = Icons.Filled.Home,
    )
}

internal val ROUTES = setOf(Route.Home)

internal fun currentRouteFromBackStack(backStackEntry: NavBackStackEntry?): Route? {
    val route = backStackEntry?.destination?.route
    return ROUTES.firstOrNull { it.route == route }
}