package com.camgist.scribbledash.feature_drawing.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.camgist.scribbledash.feature_drawing.presentation.DrawingViewModel
import com.camgist.scribbledash.feature_drawing.presentation.components.DrawingCanvas
import com.camgist.scribbledash.ui.theme.Background

@Composable
fun DrawingScreen(
    navController: NavController,
    viewModel: DrawingViewModel = viewModel()
) {
    val paths by viewModel.paths.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(16.dp)
    ) {
        // Drawing Tools
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Undo button
            IconButton(onClick = { viewModel.undoLastPath() }) {
                Icon(
                    imageVector = Icons.Default.Undo,
                    contentDescription = "Undo"
                )
            }
            
            // Clear button
            IconButton(onClick = { viewModel.clearCanvas() }) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear"
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            // More UI controls will be added here later
        }
        
        // Canvas for drawing
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            DrawingCanvas(
                paths = paths,
                currentColor = viewModel.currentColor,
                currentStrokeWidth = viewModel.currentStrokeWidth,
                onPathCreated = viewModel::addPath
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Bottom tools (color picker, etc.)
        // Will be added later
    }
} 