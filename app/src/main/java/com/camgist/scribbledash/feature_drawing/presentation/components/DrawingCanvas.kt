package com.camgist.scribbledash.feature_drawing.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.camgist.scribbledash.ui.theme.White

/**
 * A canvas that allows drawing with touch input
 */
@Composable
fun DrawingCanvas(
    modifier: Modifier = Modifier,
    paths: List<com.camgist.scribbledash.feature_drawing.domain.model.Path> = emptyList(),
    currentColor: Color = Color.Black,
    currentStrokeWidth: Float = 5f,
    onPathCreated: (com.camgist.scribbledash.feature_drawing.domain.model.Path) -> Unit = {}
) {
    // Keep track of the current path being drawn - use by/var syntax for state
    var currentPath by remember { mutableStateOf<Path?>(null) }
    var currentPoints by remember { mutableStateOf<List<com.camgist.scribbledash.feature_drawing.domain.model.PathPoint>>(emptyList()) }
    
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = White
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f) // 1:1 aspect ratio
                .padding(2.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { offset ->
                                val newPath = Path().apply {
                                    moveTo(offset.x, offset.y)
                                }
                                currentPath = newPath
                                currentPoints = listOf(
                                    com.camgist.scribbledash.feature_drawing.domain.model.PathPoint(
                                        x = offset.x, 
                                        y = offset.y
                                    )
                                )
                            },
                            onDrag = { change, _ ->
                                val path = currentPath ?: return@detectDragGestures
                                
                                // Create a new Path object with the updated line
                                val updatedPath = Path().apply {
                                    // Add all existing points
                                    val firstPoint = currentPoints.first()
                                    moveTo(firstPoint.x, firstPoint.y)
                                    
                                    for (i in 1 until currentPoints.size) {
                                        val point = currentPoints[i]
                                        lineTo(point.x, point.y)
                                    }
                                    
                                    // Add the new point
                                    lineTo(change.position.x, change.position.y)
                                }
                                
                                // Update the state to trigger recomposition
                                currentPath = updatedPath
                                currentPoints = currentPoints + com.camgist.scribbledash.feature_drawing.domain.model.PathPoint(
                                    x = change.position.x,
                                    y = change.position.y
                                )
                            },
                            onDragEnd = {
                                if (currentPoints.size > 1) {
                                    // Create final path and notify
                                    onPathCreated(
                                        com.camgist.scribbledash.feature_drawing.domain.model.Path(
                                            points = currentPoints,
                                            color = currentColor,
                                            strokeWidth = currentStrokeWidth
                                        )
                                    )
                                }
                                // Reset current path
                                currentPath = null
                                currentPoints = emptyList()
                            }
                        )
                    }
            ) {
                // Draw grid lines (9 equal squares)
                val canvasWidth = size.width
                val canvasHeight = size.height
                val cellWidth = canvasWidth / 3
                val cellHeight = canvasHeight / 3
                
                // Draw grid lines with light gray color
                val gridLineColor = Color.LightGray
                val gridLineStrokeWidth = 1.5f
                
                // Draw horizontal grid lines
                for (i in 1..2) {
                    val y = i * cellHeight
                    drawLine(
                        color = gridLineColor,
                        start = Offset(0f, y),
                        end = Offset(canvasWidth, y),
                        strokeWidth = gridLineStrokeWidth
                    )
                }
                
                // Draw vertical grid lines
                for (i in 1..2) {
                    val x = i * cellWidth
                    drawLine(
                        color = gridLineColor,
                        start = Offset(x, 0f),
                        end = Offset(x, canvasHeight),
                        strokeWidth = gridLineStrokeWidth
                    )
                }
                
                // Draw existing paths
                paths.forEach { path ->
                    if (path.points.size > 1) {
                        val compositePath = Path()
                        val firstPoint = path.points.first()
                        compositePath.moveTo(firstPoint.x, firstPoint.y)
                        
                        for (i in 1 until path.points.size) {
                            val point = path.points[i]
                            compositePath.lineTo(point.x, point.y)
                        }
                        
                        drawPath(
                            path = compositePath,
                            color = path.color,
                            style = Stroke(
                                width = path.strokeWidth,
                                cap = path.strokeCap,
                                join = path.strokeJoin
                            ),
                            alpha = path.alpha
                        )
                    }
                }
                
                // Draw current path
                currentPath?.let { path ->
                    drawPath(
                        path = path,
                        color = currentColor,
                        style = Stroke(
                            width = currentStrokeWidth,
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round
                        )
                    )
                }
            }
        }
    }
}