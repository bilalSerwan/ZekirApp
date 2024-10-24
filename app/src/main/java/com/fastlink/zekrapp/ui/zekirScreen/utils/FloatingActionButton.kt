package com.fastlink.zekrapp.ui.zekirScreen.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.fastlink.zekrapp.ui.zekirScreen.ZekirScreenViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomFloatingActionButton(
    viewModel: ZekirScreenViewModel,
    pagerState: PagerState
) {
    val coroutineScope = rememberCoroutineScope()
    val animatedBorder by animateFloatAsState(
        targetValue = (viewModel.zekirCounter.intValue.toFloat() / viewModel.zekirs[pagerState.currentPage].numericCounter.toFloat()) * 360f,
        label = "",
        animationSpec = tween(1000)
    )
    Box(
        modifier = Modifier
            .size(90.dp)
            .halfCircle(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        FloatingActionButton(
            onClick = {
                coroutineScope.launch {
                    viewModel.handleFABClick(pagerState.currentPage)
                    if (viewModel.shouldNavigateToNextZekir(pagerState.currentPage))
                        pagerState.animateScrollToPage(
                            page = pagerState.currentPage + 1,
                            pageOffsetFraction = 0f,
                            animationSpec = tween(700)
                        )
                }
            },
            containerColor = MaterialTheme.colorScheme.primaryContainer,
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