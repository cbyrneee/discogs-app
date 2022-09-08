package dev.cbyrne.discogs.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.cbyrne.discogs.app.navigation.CustomNavigationBar
import dev.cbyrne.discogs.app.navigation.NavigationHost
import dev.cbyrne.discogs.app.navigation.currentRouteFromBackStack
import dev.cbyrne.discogs.app.theme.DiscogsTheme

@AndroidEntryPoint
class DiscogsActivity : ComponentActivity() {
    private var navHostController: NavHostController? = null

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DiscogsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    navHostController = navController

                    val backStackEntry by navController.currentBackStackEntryAsState()
                    val currentScreen = currentRouteFromBackStack(backStackEntry)

                    Scaffold(
                        bottomBar = {
                            if (currentScreen?.hidesNavigationBar == false) {
                                CustomNavigationBar(navController, currentScreen)
                            }
                        }
                    ) {
                        NavigationHost(navController = navController, paddingValues = it)
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navHostController?.handleDeepLink(intent)
    }
}
