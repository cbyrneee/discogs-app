package dev.cbyrne.discogs.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val name: String,
    val icon: ImageVector
) {
    object Home : Screen(
        route = "home",
        name = "Home",
        icon = Icons.Filled.Home,
    )
}

internal val SCREENS = setOf(Screen.Home)