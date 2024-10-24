package com.fastlink.zekrapp.ui.zekirScreen.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color


@Composable
fun Modifier.halfCircle(
    color: Color
) = this.drawWithContent {

    this.drawArc(
        color = color,
        startAngle = 0f,
        sweepAngle = 180f,
        useCenter = true,
    )
    drawContent()
}