package com.fastlink.zekrapp.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.fastlink.zekrapp.LocalNavController
import com.fastlink.zekrapp.LocalViewModel
import com.fastlink.zekrapp.R
import com.fastlink.zekrapp.presentation.utils.DashedDevider
import com.fastlink.zekrapp.presentation.utils.AppBar
import com.fastlink.zekrapp.presentation.utils.CustomFloatingActionButton
import com.fastlink.zekrapp.presentation.utils.CustomSnackBar
import com.fastlink.zekrapp.presentation.utils.bottomAppBar.BottomBar
import com.fastlink.zekrapp.presentation.utils.bottomAppBar.getListOfBottomBarItemsInZekirScreen
import com.fastlink.zekrapp.presentation.utils.dashedBorder


@SuppressLint("RememberReturnType")
@Composable
fun ZekirScreen(categoryId: Int) {
    val snackBarState = remember {
        SnackbarHostState()
    }
    val viewModel = LocalViewModel.current
    val scaffoldState = rememberScaffoldState()
    val navController = LocalNavController.current
    val clipboardManager = LocalClipboardManager.current
    val scope = rememberCoroutineScope()
    remember { viewModel.getZekirByCategoryId(categoryId) }
    val category = viewModel.getCategoryById(categoryId)
    val zekirNumber = viewModel.zekirNumber
    val zekirCounter = viewModel.zekirCounter
    val animatedBorder by animateFloatAsState(
        targetValue =
        (zekirCounter.intValue.toFloat() / viewModel.zekirs[zekirNumber.intValue].numericCounter.toFloat()) * 360f,
        label = "",
        animationSpec = tween(1000)
    )

    fun buttonIsEnable(): Boolean {
        return zekirNumber.intValue + 1 < viewModel.zekirs.size || zekirCounter.intValue < viewModel.zekirs[zekirNumber.intValue].numericCounter
    }


    LaunchedEffect(key1 = zekirNumber.intValue) {
        snackBarState.showSnackbar("")
    }
    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colorScheme.background,
        topBar = {
            AppBar(
                title = category.categoryTitle,
                actions = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 20.dp, top = 2.dp)
                            .clickable {
                                navController.navigateUp()
                            },
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                },
            )
        },
        floatingActionButton = {
            CustomFloatingActionButton(
                scope = scope,
                animatedBorder = animatedBorder
            )
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomBar(bottomBarItems = getListOfBottomBarItemsInZekirScreen(
                zekirCategory = category,
                onCopyIconClicked = {
                    clipboardManager.setText(AnnotatedString(viewModel.zekirs[zekirNumber.intValue].zekirTitle))
                },
                onFavoriteIconClicked = {
                    viewModel.updateCategory(
                        categoryId = category.id,
                        isFavorite = !category.isFavorite
                    )
                }
            ))
        }
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .clickable(
                    enabled = buttonIsEnable(),
                    onClick = {
                        viewModel.onClickedZekirCard(scope)
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
                text = viewModel.zekirs[zekirNumber.intValue].zekirTitle,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = "\n(${
                    viewModel.zekirs[zekirNumber.intValue].textCounter ?: stringResource(id = R.string.readOnceInArabic)
                })",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
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
                text = viewModel.zekirs[zekirNumber.intValue].hint,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .offset(y = 200.dp),
        ) {
            SnackbarHost(hostState = snackBarState) {
                CustomSnackBar("الذکر ${zekirNumber.intValue + 1} من ${viewModel.zekirs.size}")
            }
        }
    }
}

