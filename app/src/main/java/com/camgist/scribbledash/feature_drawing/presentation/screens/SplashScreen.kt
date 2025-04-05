package com.camgist.scribbledash.feature_drawing.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.camgist.scribbledash.core.presentation.Route
import com.camgist.scribbledash.ui.theme.BagelFatOneFamily
import com.camgist.scribbledash.ui.theme.GradientEnd
import com.camgist.scribbledash.ui.theme.GradientStart
import com.camgist.scribbledash.ui.theme.TextPrimary
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    val gradient = Brush.linearGradient(
        colors = listOf(GradientStart, GradientEnd),
        start = Offset(0f, 0f),
        end = Offset(0f, Float.POSITIVE_INFINITY)
    )
    
    // Auto-navigate to home screen after a delay
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate(Route.HomeScreen.route) {
            popUpTo(Route.SplashScreen.route) { inclusive = true }
        }
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                drawRect(brush = gradient)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "ScribbleDash",
            fontSize = 48.sp,
            fontFamily = BagelFatOneFamily,
            color = TextPrimary
        )
    }
} 