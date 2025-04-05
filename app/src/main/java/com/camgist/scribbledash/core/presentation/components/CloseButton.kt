package com.camgist.scribbledash.core.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.camgist.scribbledash.ui.theme.TextPrimary

/**
 * Reusable close button with circular border
 */
@Composable
fun CloseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(
                width = 1.5.dp,
                color = Color(0xFFEEE6DD),
                shape = CircleShape
            )
            .clip(CircleShape)
    ) {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = TextPrimary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
} 