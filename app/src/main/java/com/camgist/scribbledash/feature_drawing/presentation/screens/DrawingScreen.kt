package com.camgist.scribbledash.feature_drawing.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.camgist.scribbledash.R
import com.camgist.scribbledash.core.presentation.components.CloseButton
import com.camgist.scribbledash.feature_drawing.presentation.DrawingViewModel
import com.camgist.scribbledash.feature_drawing.presentation.components.DrawingCanvas
import com.camgist.scribbledash.ui.theme.Background
import com.camgist.scribbledash.ui.theme.Green
import com.camgist.scribbledash.ui.theme.TextPrimary
import com.camgist.scribbledash.ui.theme.White

@Composable
fun DrawingScreen(
    navController: NavController,
    viewModel: DrawingViewModel = viewModel()
) {
    val paths by viewModel.paths.collectAsState()
    
    // Define custom colors
    val buttonEnabledColor = Green
    val buttonDisabledColor = Color(0xFFE1D5CA)
    val iconBackgroundEnabled = Color(0xFFEEE7E0)
    val iconBackgroundDisabled = Color(0xFFEEE7E0).copy(alpha = 0.4f)
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(16.dp)
    ) {
        // Top Bar with Close Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            
            // Close button using the shared component
            CloseButton(
                onClick = { navController.popBackStack() }
            )
        }
        
        // Time to draw! text
        Text(
            text = "Time to draw!",
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        
        // Canvas for drawing
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            DrawingCanvas(
                paths = paths,
                currentColor = viewModel.currentColor,
                currentStrokeWidth = viewModel.currentStrokeWidth,
                onPathCreated = viewModel::addPath
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Bottom controls (Undo, Redo, Clear Canvas)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Get the hasUndonePaths state
            val hasUndonePaths by viewModel.hasUndonePaths.collectAsState()
            
            // Get the hasUndoablePaths state
            val hasUndoablePaths by viewModel.hasUndoablePaths.collectAsState()
            
            // Undo button with background
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        color = if (hasUndoablePaths) iconBackgroundEnabled else iconBackgroundDisabled
                    )
                    .padding(8.dp)
            ) {
                IconButton(
                    onClick = { viewModel.undoLastPath() },
                    enabled = hasUndoablePaths
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_undo),
                        contentDescription = "Undo",
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.padding(4.dp))
            
            // Redo button with background
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        color = if (hasUndonePaths) iconBackgroundEnabled else iconBackgroundDisabled
                    )
                    .padding(8.dp)
            ) {
                IconButton(
                    onClick = { viewModel.redoLastPath() },
                    enabled = hasUndonePaths
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_redo),
                        contentDescription = "Redo",
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Clear Canvas button with white border
            Button(
                onClick = { viewModel.clearCanvas() },
                enabled = hasUndoablePaths,
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (hasUndoablePaths) buttonEnabledColor else buttonDisabledColor,
                    contentColor = White,
                    disabledContainerColor = buttonDisabledColor,
                    disabledContentColor = White
                ),
                modifier = Modifier
                    .border(
                        width = 3.dp,
                        color = White,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = "CLEAR CANVAS",
                    style = MaterialTheme.typography.titleSmall,
                    color = White,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
} 