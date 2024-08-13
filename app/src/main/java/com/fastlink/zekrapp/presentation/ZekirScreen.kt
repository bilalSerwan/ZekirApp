package com.fastlink.zekrapp.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fastlink.zekrapp.LocalToast
import com.fastlink.zekrapp.LocalViewModel
import com.fastlink.zekrapp.R
import com.fastlink.zekrapp.presentation.utils.DashedDevider
import com.fastlink.zekrapp.presentation.utils.appBars.ZekirScreenAppBar
import com.fastlink.zekrapp.presentation.utils.archBorder
import com.fastlink.zekrapp.presentation.utils.bottomAppBar.ZekirScreenBottomAppBar
import com.fastlink.zekrapp.presentation.utils.dashedBorder
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@Composable
fun ZekirScreen(categoryId: Int) {
    val toast = LocalToast.current
    val clipboardManager = LocalClipboardManager.current
    val viewModel = LocalViewModel.current
    val scope = rememberCoroutineScope()
    val category = viewModel.getCategoryById(categoryId)
    val zekirs = viewModel.getZekirByCategoryId(category.id)
    val zekirNumber = remember {
        mutableIntStateOf(0)
    }
    val zekirCounter = remember {
        mutableIntStateOf(0)
    }
    LaunchedEffect(true) {
        toast(" الذكر ${zekirNumber.intValue + 1} من ${zekirs.size}")
    }
    val animatedBorder by animateFloatAsState(
        targetValue =
        (zekirCounter.intValue.toFloat() / zekirs[zekirNumber.intValue].counterNumber.toFloat()) * 360f,
        label = "",
        animationSpec = tween(1000)
    )

    fun onClicked() {
        if (zekirCounter.intValue < zekirs[zekirNumber.intValue].counterNumber) {
            zekirCounter.intValue++
            if (zekirCounter.intValue == zekirs[zekirNumber.intValue].counterNumber) {
                scope.launch {
                    delay(1500)
                    if (zekirNumber.intValue < zekirs.size - 1) {
                        zekirCounter.intValue = 0
                        zekirNumber.intValue++
                        scope.launch {
                            toast(" الذكر ${zekirNumber.intValue + 1} من ${zekirs.size}")
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background,
        topBar = {
            ZekirScreenAppBar(title = category.categoryTitle)
        },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center,
            ) {
                FloatingActionButton(
                    onClick = {
                        onClicked()
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
                        text = zekirCounter.intValue.toString(),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        },


        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            ZekirScreenBottomAppBar(
                category = category,
                onCopyIconClicked = { clipboardManager.setText(AnnotatedString(zekirs[zekirNumber.intValue].zekirTitle)) }
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .clickable(
                    enabled = (zekirCounter.intValue < zekirs[zekirNumber.intValue].counterNumber) || zekirNumber.intValue < zekirs.size - 1,
                    onClick = {
                        onClicked()
                    }
                )
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 60.dp,
                    top = 20.dp
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
                    ),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp),
        ) {
            Text(
                text = zekirs[zekirNumber.intValue].zekirTitle,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text =
                    if (zekirs[zekirNumber.intValue].counterAsString == "null") stringResource(id = R.string.readOnceInArabic) else zekirs[zekirNumber.intValue].counterAsString,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodyMedium
            )
            DashedDevider(
                thickness = 1.1.dp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            Text(
                text = zekirs[zekirNumber.intValue].hint,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

        }
    }

}



