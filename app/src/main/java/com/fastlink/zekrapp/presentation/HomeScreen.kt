package com.fastlink.zekrapp.presentation

import android.util.Log
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
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fastlink.zekrapp.LocalNavController
import com.fastlink.zekrapp.LocalViewModel
import com.fastlink.zekrapp.R
import com.fastlink.zekrapp.presentation.utils.CategoryItem
import com.fastlink.zekrapp.presentation.utils.AppBar
import com.fastlink.zekrapp.presentation.utils.bottomAppBar.BottomBar
import com.fastlink.zekrapp.presentation.utils.bottomAppBar.getListOfBottomBarItems

@Composable
fun HomeScreen() {
    val viewModel = LocalViewModel.current

    val navController = LocalNavController.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    Log.d("HomeScreen", "HomeScreen: $navBackStackEntry")
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background,
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
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            ) {
                items(viewModel.getAllZekirCategories()) { category ->
                    CategoryItem(category = category)
                }
                item {
                    Spacer(modifier = Modifier.padding(10.dp))
                }
            }
        }
    }
}