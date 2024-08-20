package com.fastlink.zekrapp.presentation

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
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fastlink.zekrapp.LocalNavController
import com.fastlink.zekrapp.LocalViewModel
import com.fastlink.zekrapp.R
import com.fastlink.zekrapp.presentation.utils.CategoryItem
import com.fastlink.zekrapp.presentation.utils.AppBar
import com.fastlink.zekrapp.presentation.utils.bottomAppBar.BottomBar
import com.fastlink.zekrapp.presentation.utils.bottomAppBar.getListOfBottomBarItems

@Composable
fun FavoriteScreen() {
    val viewModel = LocalViewModel.current
    val navController = LocalNavController.current
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
            if (viewModel.getFavoriteCategories().isEmpty()) {
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
                    items(viewModel.getFavoriteCategories()) { category ->
                        CategoryItem(category = category)
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