package com.example.cricradio2

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.cricradio2.presentation.HomeScreen
import com.example.cricradio2.ui.theme.CricRadio2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CricRadio2Theme {
                SetSystemBarColors()
                HomeScreen()
            }
        }
    }
}
@Composable
fun SetSystemBarColors() {
    val view = LocalView.current

    SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor = Color.Black.toArgb()
        WindowCompat.getInsetsController(window, view).apply {
            isAppearanceLightStatusBars = false
        }
    }
}