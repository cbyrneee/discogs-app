package dev.cbyrne.discogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import dev.cbyrne.discogs.ui.DiscogsApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, false)

            setContent {
                DiscogsApp(window)
            }
        }
    }
}
