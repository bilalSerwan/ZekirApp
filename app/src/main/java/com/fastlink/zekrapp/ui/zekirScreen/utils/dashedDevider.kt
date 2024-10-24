package com.fastlink.zekrapp.ui.zekirScreen.utils

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp


@Composable
fun DashedDivider(
    thickness: Dp,
    color: Color,
    phase: Float = 10f,
    interval: FloatArray = floatArrayOf(10f, 10f),
    modifier: Modifier
) {
    Canvas(modifier = modifier) {
        drawRoundRect(
            color = color,
            style = Stroke(
                width = thickness.toPx(),
                pathEffect = PathEffect.dashPathEffect(interval, phase)
            )
        )
    }
}