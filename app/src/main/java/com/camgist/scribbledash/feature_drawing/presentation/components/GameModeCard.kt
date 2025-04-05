package com.camgist.scribbledash.feature_drawing.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.camgist.scribbledash.R
import com.camgist.scribbledash.ui.theme.BagelFatOneFamily
import com.camgist.scribbledash.ui.theme.Green
import com.camgist.scribbledash.ui.theme.TextPrimary
import com.camgist.scribbledash.ui.theme.White

@Composable
fun GameModeCard(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(32.dp),
        border = BorderStroke(8.dp, Green),
        colors = CardDefaults.cardColors(
            containerColor = White
        )
    ) {
        // Use a Box to allow absolute positioning of the pencil
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Text content in the top-left
            Column(
                modifier = Modifier
                    .padding(start = 24.dp, top = 24.dp, end = 80.dp, bottom = 24.dp)
                    .align(Alignment.TopStart)
            ) {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontFamily = BagelFatOneFamily,
                    color = TextPrimary,
                    textAlign = TextAlign.Start,
                    lineHeight = 32.sp
                )
            }
            
            // Pencil illustration positioned to be flush with bottom-right
            Image(
                painter = painterResource(id = R.drawable.pencil_drawing),
                contentDescription = "Pencil drawing",
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.BottomEnd)
            )
        }
    }
} 