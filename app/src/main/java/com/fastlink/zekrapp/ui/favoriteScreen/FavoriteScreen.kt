package com.fastlink.zekrapp.ui.favoriteScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fastlink.zekrapp.R
import com.fastlink.zekrapp.ui.utils.AppBar
import com.fastlink.zekrapp.ui.utils.ZekirCategoryCard
import com.fastlink.zekrapp.ui.utils.bottomAppBar.BottomBar
import com.fastlink.zekrapp.ui.utils.bottomAppBar.getListOfBottomBarItems

@Composable
fun FavoriteScreen(
    navController: NavController,
    favoriteScreenViewModel: FavoriteScreenViewModel = hiltViewModel(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background,
        topBar = { AppBar(title = stringResource(id = R.string.FavoriteAppBarTitle)) },
        bottomBar = {
            navBackStackEntry?.let { entry ->
                BottomBar(
                    bottomBarItems = getListOfBottomBarItems(
                        homeIconLabel = stringResource(id = R.string.Home),
                        favoriteIconLabel = stringResource(id = R.string.Favorite),
                        navController = navController,
                        navBackStackEntry = entry
                    )
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            if (favoriteScreenViewModel.getFavoriteZekirCategories().isEmpty()) {
                Text(
                    text = "No Favorite Categories Yet",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    items(favoriteScreenViewModel.getFavoriteZekirCategories(), key = {
                        it.id
                    }) { zekirCategory ->
                        ZekirCategoryCard(
                            zekirCategory = zekirCategory,
                            navController = navController,
                            onFavoriteIconClicked = {
                                favoriteScreenViewModel.updateZekirCategory(
                                    zekirCategory.id,
                                    !zekirCategory.isFavorite
                                )
                            }
                        )
                    }
                    item {
                        Spacer(
                            modifier = Modifier
                                .height(10.dp)
                        )
                    }
                }
            }
        }
    }
}