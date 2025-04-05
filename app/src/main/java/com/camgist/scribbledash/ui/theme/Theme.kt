package com.camgist.scribbledash.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Single color scheme for ScribbleDash (no dark theme for now)
private val ScribbleDashColorScheme = lightColorScheme(
    primary = Green,
    onPrimary = White,
    primaryContainer = LightGreen,
    onPrimaryContainer = DarkGreen,
    secondary = DarkGreen,
    onSecondary = White,
    background = Background,
    onBackground = TextPrimary,
    surface = White,
    onSurface = TextPrimary,
    surfaceVariant = Background,
    onSurfaceVariant = TextSecondary
)

@Composable
fun ScribbleDashTheme(
    // We're not supporting dark theme for now
    darkTheme: Boolean = false,
    // Dynamic color is disabled for consistent branding
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = ScribbleDashColorScheme
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}