package com.fastlink.zekrapp.ui.utils.bottomAppBar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.fastlink.zekrapp.appData.model.ZekirCategoryModel
import com.fastlink.zekrapp.navigation.Screen

data class BottomBarItem(
    val icon: ImageVector,
    val label: String?,
    val selected: Boolean,
    val onClicked: () -> Unit
)
@Composable
fun BottomBar(bottomBarItems: List<BottomBarItem>) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)),
        contentPadding = PaddingValues(horizontal = 90.dp)
    ) {
        for (i in bottomBarItems.indices) {
            BottomAppBarItem(
                icon = bottomBarItems[i].icon,
                text = bottomBarItems[i].label,
                selected = bottomBarItems[i].selected,
                onClicked = bottomBarItems[i].onClicked
            )
            if (i < bottomBarItems.size - 1) {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

fun getListOfBottomBarItemsInZekirScreen(
    zekirCategory: ZekirCategoryModel,
    onCopyIconClicked: () -> Unit,
    onFavoriteIconClicked: () -> Unit
): List<BottomBarItem> {
    val bottomBarItems = listOf(
        BottomBarItem(
            icon = Icons.Default.ContentCopy,
            label = null,
            selected = true,
            onClicked = onCopyIconClicked
        ),
        BottomBarItem(
            icon = if (zekirCategory.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            label = null,
            selected = true,
            onClicked = onFavoriteIconClicked
        ),
    )
    return bottomBarItems
}


fun getListOfBottomBarItems(
    homeIconLabel: String,
    favoriteIconLabel: String,
    navController: NavController,
    navBackStackEntry: NavBackStackEntry,
): List<BottomBarItem> {

    val bottomBarItems = listOf(
        BottomBarItem(
            icon = Icons.Default.Home,
            label = homeIconLabel,
            selected = navBackStackEntry.destination.route == Screen.Home.route,
            onClicked = {
                if (navBackStackEntry.destination.route != Screen.Home.route) {
                    navController.navigate(Screen.Home.route)
                }
            }),
        BottomBarItem(
            icon = Icons.Default.Favorite,
            label = favoriteIconLabel,
            selected = navBackStackEntry.destination.route == Screen.Favorite.route,
            onClicked = {
                if (navBackStackEntry.destination.route != Screen.Favorite.route) {
                    navController.navigate(Screen.Favorite.route)
                }
            }),
    )
    return bottomBarItems
}