package com.camgist.scribbledash.feature_drawing.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.camgist.scribbledash.ui.theme.Background
import com.camgist.scribbledash.ui.theme.TextPrimary

@Composable
fun DifficultyScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Difficulty Screen",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = TextPrimary
        )
    }
} 