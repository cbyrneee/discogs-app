package dev.cbyrne.discogs.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.cbyrne.discogs.app.navigation.CustomNavigationBar
import dev.cbyrne.discogs.app.navigation.NavigationHost
import dev.cbyrne.discogs.app.theme.DiscogsTheme

@AndroidEntryPoint
class DiscogsActivity : ComponentActivity() {
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

                    Scaffold(
                        bottomBar = { CustomNavigationBar(navController = navController) }
                    ) {
                        NavigationHost(navController = navController, paddingValues = it)
                    }
                }
            }
        }
    }
}
