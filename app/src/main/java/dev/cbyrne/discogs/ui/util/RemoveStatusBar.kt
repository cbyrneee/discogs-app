package dev.cbyrne.discogs.ui.util

import android.view.Window
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun RemoveStatusBar(window: Window) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        systemUiController.setStatusBarColor(color = Color.Transparent)
    }
}