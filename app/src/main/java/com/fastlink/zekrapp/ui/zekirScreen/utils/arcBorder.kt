package com.fastlink.zekrapp.ui.zekirScreen.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.archBorder(
    startAngle: Float = 0f,
    borderColor : Color = MaterialTheme.colorScheme.primary,
    animatedBorder : Float
)= this.drawWithContent {
    drawContent()
    this.drawArc(
        color =borderColor,
        startAngle =startAngle-90f,
        sweepAngle = animatedBorder,
        useCenter = false,
        style = Stroke(
            width = 5.dp.toPx(),
            miter = animatedBorder,
            cap = StrokeCap.Round,
        )
    )
}