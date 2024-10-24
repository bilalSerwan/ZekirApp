package com.fastlink.zekrapp.ui.zekirScreen.utils

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fastlink.zekrapp.R
import com.fastlink.zekrapp.ui.zekirScreen.ZekirScreenViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ZekirCard(pagerState: PagerState, viewModel: ZekirScreenViewModel) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .clickable(enabled = viewModel.isCardButtonEnabled(pagerState.currentPage), onClick = {
                coroutineScope.launch {
                    viewModel.handleZekirCardClick(pagerState.currentPage)
                    if (viewModel.shouldNavigateToNextZekir(pagerState.currentPage))
                        pagerState.animateScrollToPage(
                            pagerState.currentPage + 1,
                            0f,
                            animationSpec = tween(1000)
                        )
                }
            })
            .padding(
                start = 16.dp, end = 16.dp, bottom = 60.dp, top = 20.dp
            )
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f))
            .padding(12.dp)
            .dashedBorder(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                    )
                ), shape = RoundedCornerShape(4.dp)
            )
            .padding(16.dp),
    ) {
        Text(
            text = viewModel.zekirs[pagerState.currentPage].zekirTitle,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = "\n(${
                viewModel.zekirs[pagerState.currentPage].textCounter ?: stringResource(id = R.string.readOnceInArabic)
            })",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.bodyMedium
        )
        DashedDivider(
            thickness = 1.1.dp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        Text(
            text = viewModel.zekirs[pagerState.currentPage].hint,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}