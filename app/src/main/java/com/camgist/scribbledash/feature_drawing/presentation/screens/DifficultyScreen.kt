package com.camgist.scribbledash.feature_drawing.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.camgist.scribbledash.R
import com.camgist.scribbledash.core.presentation.Route
import com.camgist.scribbledash.ui.theme.BagelFatOneFamily
import com.camgist.scribbledash.ui.theme.GradientEnd
import com.camgist.scribbledash.ui.theme.GradientStart
import com.camgist.scribbledash.ui.theme.TextPrimary
import com.camgist.scribbledash.ui.theme.TextSecondary
import com.camgist.scribbledash.ui.theme.White

@Composable
fun DifficultyScreen(
    navController: NavController
) {
    val gradient = Brush.linearGradient(
        colors = listOf(GradientStart, GradientEnd),
        start = Offset(0f, 0f),
        end = Offset(0f, Float.POSITIVE_INFINITY)
    )
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                drawRect(brush = gradient)
            }
    ) {
        // Close button in top right with circular border
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .border(
                    width = 1.5.dp,
                    color = Color(0xFFEEE6DD),
                    shape = CircleShape
                )
                .clip(CircleShape)
        ) {
            IconButton(
                onClick = {
                    navController.navigateUp()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = TextPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        
        // Main content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Headline - smaller
            Text(
                text = "Start drawing!",
                fontSize = 36.sp,
                fontFamily = BagelFatOneFamily,
                color = TextPrimary,
                textAlign = TextAlign.Center
            )
            
            // Subtitle - smaller
            Text(
                text = "Choose a difficulty setting",
                fontSize = 18.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(80.dp))
            
            // Difficulty options in a row with more spacing
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround, // Increased spacing
                verticalAlignment = Alignment.Bottom
            ) {
                // Beginner Option
                DifficultyOption(
                    title = "Beginner",
                    iconResId = R.drawable.icon_beginner,
                    onClick = {
                        navController.navigate(Route.DrawingScreen.route)
                    }
                )
                
                // Challenging Option - offset upward
                DifficultyOption(
                    title = "Challenging",
                    iconResId = R.drawable.icon_challenging,
                    modifier = Modifier.offset(y = (-20).dp),
                    onClick = {
                        navController.navigate(Route.DrawingScreen.route)
                    }
                )
                
                // Master Option
                DifficultyOption(
                    title = "Master",
                    iconResId = R.drawable.icon_master,
                    iconPadding = 10,
                    onClick = {
                        navController.navigate(Route.DrawingScreen.route)
                    }
                )
            }
        }
    }
}

@Composable
fun DifficultyOption(
    title: String,
    iconResId: Int,
    modifier: Modifier = Modifier,
    iconPadding: Int = 0,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Circular white card with icon
        Card(
            shape = CircleShape,
            modifier = Modifier
                .size(90.dp)
                .clickable(onClick = onClick),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            colors = CardDefaults.cardColors(containerColor = White)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = title,
                    modifier = Modifier.size(90.dp - iconPadding.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Title below the icon - smaller and lighter
        Text(
            text = title,
            fontSize = 16.sp,
            fontFamily = BagelFatOneFamily,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )
    }
} 