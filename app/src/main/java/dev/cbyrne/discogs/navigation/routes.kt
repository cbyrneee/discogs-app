package dev.cbyrne.discogs.navigation

import androidx.navigation.NavBackStackEntry
import dev.cbyrne.discogs.common.navigation.Route

internal val ROUTES = setOf(Route.Discover.Home, Route.Settings)

internal fun currentRouteFromBackStack(backStackEntry: NavBackStackEntry?): Route? {
    val route = backStackEntry?.destination?.route
    return ROUTES.firstOrNull { it.route == route }
}