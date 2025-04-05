package com.camgist.scribbledash.feature_drawing.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.camgist.scribbledash.core.presentation.Route
import com.camgist.scribbledash.core.presentation.components.ScribbleDashBottomNavigation
import com.camgist.scribbledash.feature_drawing.presentation.components.GameModeCard
import com.camgist.scribbledash.ui.theme.BagelFatOneFamily
import com.camgist.scribbledash.ui.theme.GradientEnd
import com.camgist.scribbledash.ui.theme.GradientStart
import com.camgist.scribbledash.ui.theme.TextPrimary
import com.camgist.scribbledash.ui.theme.TextSecondary
import com.camgist.scribbledash.ui.theme.White

@Composable
fun HomeScreen(
    navController: NavController
) {
    val gradient = Brush.linearGradient(
        colors = listOf(GradientStart, GradientEnd),
        start = Offset(0f, 0f),
        end = Offset(0f, Float.POSITIVE_INFINITY)
    )
    
    Scaffold(
        bottomBar = {
            ScribbleDashBottomNavigation(navController = navController)
        },
        containerColor = White // Ensure the scaffold container color is white
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawRect(brush = gradient)
                }
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // App title at top left
                Text(
                    text = "ScribbleDash",
                    fontSize = 28.sp,
                    fontFamily = BagelFatOneFamily,
                    color = TextPrimary
                )
                
                Spacer(modifier = Modifier.height(80.dp))
                
                // Centered content
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Headline
                    Text(
                        text = "Start drawing!",
                        fontSize = 42.sp,
                        fontFamily = BagelFatOneFamily,
                        color = TextPrimary,
                        textAlign = TextAlign.Center
                    )
                    
                    // Subtitle - right below headline
                    Text(
                        text = "Select game mode",
                        fontSize = 20.sp,
                        color = TextSecondary,
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(48.dp))
                    
                    // Game mode card
                    GameModeCard(
                        title = "One Round\nWonder",
                        onClick = {
                            navController.navigate(Route.DifficultyScreen.route)
                        }
                    )
                }
            }
        }
    }
} 