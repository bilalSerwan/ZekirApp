package com.fastlink.zekrapp.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fastlink.zekrapp.LocalNavController
import com.fastlink.zekrapp.presentation.utils.AppBar
import com.fastlink.zekrapp.presentation.utils.CustomFloatingActionButton
import com.fastlink.zekrapp.presentation.utils.CustomSnackBar
import com.fastlink.zekrapp.presentation.utils.ZekirCard
import com.fastlink.zekrapp.presentation.utils.bottomAppBar.BottomBar
import com.fastlink.zekrapp.presentation.utils.bottomAppBar.getListOfBottomBarItemsInZekirScreen
import com.fastlink.zekrapp.viewModel.ZekirCategoryViewModel
import com.fastlink.zekrapp.viewModel.ZekirViewModel
import com.fastlink.zekrapp.viewModel.ZekirViewModelFactory


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ZekirScreen(categoryId: Int) {
    val categoryViewModel: ZekirCategoryViewModel = viewModel()
    val zekirViewModel: ZekirViewModel = viewModel(factory = ZekirViewModelFactory(categoryId))
    val snackBarState = remember { SnackbarHostState() }
    val pagerState = rememberPagerState(
        pageCount = { zekirViewModel.zekirs.size },
    )
    val scaffoldState = rememberScaffoldState()
    val navController = LocalNavController.current
    val clipboardManager = LocalClipboardManager.current
    val zekirCategory = categoryViewModel.getCategoryById(categoryId)

    LaunchedEffect(key1 = pagerState.currentPage) {
        zekirViewModel.resetZekirCounter()
        snackBarState.showSnackbar(message = "الذکر ${pagerState.currentPage + 1} من ${zekirViewModel.zekirs.size}")
    }
    Scaffold(scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colorScheme.background,
        topBar = {
            AppBar(
                title = zekirCategory.categoryTitle,
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
                viewModel = zekirViewModel,
                pagerState = pagerState,
            )
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomBar(
                bottomBarItems = getListOfBottomBarItemsInZekirScreen(zekirCategory = zekirCategory,
                    onCopyIconClicked = {
                        clipboardManager.setText(AnnotatedString(zekirViewModel.zekirs[pagerState.currentPage].zekirTitle))
                    },
                    onFavoriteIconClicked = {
                        categoryViewModel.updateCategory(
                            categoryId = zekirCategory.id, isFavorite = !zekirCategory.isFavorite
                        )
                    })
            )
        }) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            reverseLayout = true
        ) {
            ZekirCard(pagerState = pagerState, viewModel = zekirViewModel)
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .offset(y = 200.dp),
        ) {
            SnackbarHost(hostState = snackBarState) {
                snackBarState.currentSnackbarData?.visuals?.message?.let {
                    CustomSnackBar(it)
                }
            }
        }
    }
}