package com.fastlink.zekrapp.presentation.utils.bottomAppBar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

import androidx.navigation.compose.currentBackStackEntryAsState
import com.fastlink.zekrapp.LocalNavController
import com.fastlink.zekrapp.R
import com.fastlink.zekrapp.presentation.navigation.Screen

@Composable
fun BottomAppBarForHomeAndFavoriteScreens(

) {
    val navController = LocalNavController.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)),
        contentPadding = PaddingValues(horizontal = 90.dp)
    ) {
        BottomAppBarItem(icon = Icons.Default.Home,
            text = stringResource(id = R.string.Home),
            selected = navBackStackEntry?.destination?.route == Screen.Home.route,
            onClicked = {
                navController.navigate(Screen.Home.route)
            })
        Spacer(modifier = Modifier.weight(1f))
        BottomAppBarItem(
            icon = Icons.Default.Favorite,
            text = stringResource(id = R.string.Favorite),
            selected = navBackStackEntry?.destination?.route == Screen.Favorite.route,
            onClicked = {
                navController.navigate(Screen.Favorite.route)
            })
    }
}