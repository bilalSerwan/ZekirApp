package com.fastlink.zekrapp.ui.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
fun HomeScreen(
    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(backgroundColor = MaterialTheme.colorScheme.background,
        topBar = { AppBar(title = stringResource(id = R.string.HomeAppBarTitle)) },
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
        }) {
        Box(
            modifier = Modifier
                .padding(it)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            ) {
                items(homeScreenViewModel.getAllZekirCategories()) { zekirCategory ->
                    ZekirCategoryCard(
                        zekirCategory = zekirCategory,
                        navController = navController,
                        onFavoriteIconClicked = {
                            homeScreenViewModel.updateZekirCategory(
                                zekirCategory.id,
                                !zekirCategory.isFavorite
                            )
                        }
                    )
                }
                item {
                    Spacer(modifier = Modifier.padding(10.dp))
                }
            }
        }
    }
}