package com.fastlink.zekrapp.ui.zekirScreen

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fastlink.zekrapp.ui.utils.AppBar
import com.fastlink.zekrapp.ui.utils.bottomAppBar.BottomBar
import com.fastlink.zekrapp.ui.utils.bottomAppBar.getListOfBottomBarItemsInZekirScreen
import com.fastlink.zekrapp.ui.zekirScreen.utils.CustomFloatingActionButton
import com.fastlink.zekrapp.ui.zekirScreen.utils.CustomSnackBar
import com.fastlink.zekrapp.ui.zekirScreen.utils.ZekirCard


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ZekirScreen(
    navController: NavController,
    zekirScreenViewModel: ZekirScreenViewModel = hiltViewModel(),
) {
    val snackBarState = remember { SnackbarHostState() }
    val pagerState = rememberPagerState(
        pageCount = { zekirScreenViewModel.zekirs.size },
    )
    val scaffoldState = rememberScaffoldState()
    val clipboardManager = LocalClipboardManager.current
    val zekirCategory = zekirScreenViewModel.zekirCategory.value

    LaunchedEffect(key1 = pagerState.currentPage) {
        zekirScreenViewModel.resetZekirCounter(pagerState.currentPage)
        snackBarState.showSnackbar(message = "الذکر ${pagerState.currentPage + 1} من ${zekirScreenViewModel.zekirs.size}")
    }
    Scaffold(scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colorScheme.background,
        topBar = {
            AppBar(
                title = zekirCategory.zekirCategoryTitle,
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
                viewModel = zekirScreenViewModel,
                pagerState = pagerState,
            )
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomBar(
                bottomBarItems = getListOfBottomBarItemsInZekirScreen(zekirCategory = zekirCategory,
                    onCopyIconClicked = {
                        clipboardManager.setText(AnnotatedString(zekirScreenViewModel.zekirs[pagerState.currentPage].zekirTitle))
                    },
                    onFavoriteIconClicked = {
                        zekirScreenViewModel.updateZekirCategory(
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
            ZekirCard(pagerState = pagerState, viewModel = zekirScreenViewModel)
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .offset(y = 200.dp),
        ) {
            SnackbarHost(hostState = snackBarState) {
                snackBarState.currentSnackbarData?.visuals?.message?.let {
                    CustomSnackBar(it, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}