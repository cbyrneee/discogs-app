package dev.cbyrne.discogs.app.navigation

import androidx.navigation.NavBackStackEntry
import dev.cbyrne.discogs.common.navigation.Route

internal val ROUTES = setOf(Route.Home, Route.Settings)

internal fun currentRouteFromBackStack(backStackEntry: NavBackStackEntry?): Route? {
    val route = backStackEntry?.destination?.route
    return ROUTES.firstOrNull { it.route == route }
}