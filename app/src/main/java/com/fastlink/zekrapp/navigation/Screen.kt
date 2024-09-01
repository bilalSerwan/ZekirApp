package com.fastlink.zekrapp.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("Home")
    data object Favorite : Screen("Favorite")
    data object ZekirScreen : Screen("ZekirScreen")
}