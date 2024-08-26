package com.fastlink.zekrapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fastlink.zekrapp.presentation.FavoriteScreen
import com.fastlink.zekrapp.presentation.HomeScreen
import com.fastlink.zekrapp.presentation.ZekirScreen
import com.fastlink.zekrapp.viewModel.ZekirCategoryViewModel
import com.fastlink.zekrapp.viewModel.ZekirViewModel

@Composable
fun Navigation(
    navController: NavController, zekirCategoryViewModel: ZekirCategoryViewModel,
    zekirViewModel: ZekirViewModel
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController, zekirCategoryViewModel)
        }
        composable(Screen.Favorite.route) {
            FavoriteScreen(navController = navController, zekirCategoryViewModel)
        }
        composable("${Screen.ZekirScreen.route}/{categoryId}") {
            val categoryId = it.arguments?.getString("categoryId")?.toInt() ?: 0
            zekirViewModel.setCategoryIdAndGetZekirs(categoryId)
            ZekirScreen(
                categoryId = categoryId,
                navController = navController,
                zekirCategoryViewModel = zekirCategoryViewModel,
                zekirViewModel = zekirViewModel
            )
        }
    }
}
