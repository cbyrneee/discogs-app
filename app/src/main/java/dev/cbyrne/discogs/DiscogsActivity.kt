package dev.cbyrne.discogs

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import dev.cbyrne.discogs.navigation.currentRouteFromBackStack
import dev.cbyrne.discogs.theme.DiscogsTheme
import dev.cbyrne.discogs.ui.view.navigation.CustomNavigationBar

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
                ) {
                    val navController = rememberNavController()
                    navHostController = navController

                    val systemUiController = rememberSystemUiController()
                    SideEffect {
                        window.setFlags(
                            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                        )

                        systemUiController.setStatusBarColor(color = Color.Transparent)
                    }

                    val backStackEntry by navController.currentBackStackEntryAsState()
                    val currentScreen = currentRouteFromBackStack(backStackEntry)

                    Scaffold(
                        topBar = {
                            TopAppBar(title = {})
                        },
                        bottomBar = {
                            if (currentScreen?.hidesNavigationBar == false) {
                                CustomNavigationBar(navController, currentScreen)
                            }
                        }
                    ) {
                        RootView(navController = navController, paddingValues = it)
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
