package com.fastlink.zekrapp.presentation.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.fastlink.zekrapp.LocalViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun CustomFloatingActionButton(scope:CoroutineScope,animatedBorder:Float) {
    val viewModel = LocalViewModel.current
    Box(
        modifier = Modifier
            .size(90.dp)
            .halfCircle(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        androidx.compose.material.FloatingActionButton(
            onClick = {
                viewModel.onClickedFAB(scope)
            },
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 10.dp,
            ),
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier
                .size(75.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onPrimary)
                .archBorder(animatedBorder = animatedBorder),
        ) {
            Text(
                text = viewModel.zekirCounter.intValue.toString(),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}