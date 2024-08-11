package com.fastlink.zekrapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fastlink.zekrapp.LocalNavController
import com.fastlink.zekrapp.presentation.FavoriteScreen
import com.fastlink.zekrapp.presentation.HomeScreen
import com.fastlink.zekrapp.presentation.ZekirScreen

@Composable
fun Navigation() {
    val navHostController = LocalNavController.current
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.Favorite.route) {
            FavoriteScreen()
        }
        composable("${Screen.ZekirScreen.route}/{categoryId}") {
            val categoryId = it.arguments?.getString("categoryId")?.toInt() ?: 0
            ZekirScreen(categoryId = categoryId)
        }
    }
}
