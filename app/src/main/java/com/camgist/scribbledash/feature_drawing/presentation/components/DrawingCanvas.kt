package com.camgist.scribbledash.feature_drawing.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
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
    // Keep track of the current path being drawn
    val currentPath = remember { mutableStateOf<Path?>(null) }
    val currentPoints = remember { mutableStateOf<List<com.camgist.scribbledash.feature_drawing.domain.model.PathPoint>>(emptyList()) }
    
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        currentPath.value = Path().apply {
                            moveTo(offset.x, offset.y)
                        }
                        currentPoints.value = listOf(
                            com.camgist.scribbledash.feature_drawing.domain.model.PathPoint(
                                x = offset.x, 
                                y = offset.y
                            )
                        )
                    },
                    onDrag = { change, _ ->
                        val path = currentPath.value ?: return@detectDragGestures
                        path.lineTo(change.position.x, change.position.y)
                        currentPoints.value = currentPoints.value + com.camgist.scribbledash.feature_drawing.domain.model.PathPoint(
                            x = change.position.x,
                            y = change.position.y
                        )
                    },
                    onDragEnd = {
                        // Create final path and notify
                        onPathCreated(
                            com.camgist.scribbledash.feature_drawing.domain.model.Path(
                                points = currentPoints.value,
                                color = currentColor,
                                strokeWidth = currentStrokeWidth
                            )
                        )
                        // Reset current path
                        currentPath.value = null
                        currentPoints.value = emptyList()
                    }
                )
            }
    ) {
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
        currentPath.value?.let { path ->
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