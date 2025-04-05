package com.camgist.scribbledash.feature_drawing.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin

/**
 * Represents a drawing path with color, width and points
 */
data class Path(
    val points: List<PathPoint> = emptyList(),
    val color: Color = Color.Black,
    val strokeWidth: Float = 5f,
    val strokeCap: StrokeCap = StrokeCap.Round,
    val strokeJoin: StrokeJoin = StrokeJoin.Round,
    val alpha: Float = 1f
)

/**
 * Represents a point in a drawing path
 */
data class PathPoint(
    val x: Float,
    val y: Float
) 