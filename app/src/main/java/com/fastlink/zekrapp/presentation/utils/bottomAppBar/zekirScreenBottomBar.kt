package com.fastlink.zekrapp.presentation.utils.bottomAppBar

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fastlink.zekrapp.LocalModifier
import com.fastlink.zekrapp.LocalViewModel
import com.fastlink.zekrapp.R
import com.fastlink.zekrapp.data.model.CategoryModel


//this function is used to check if the device has a navigation bar or not
//hint: i didn't write this function, i found it on the internet
fun Context.hasNavigationBar(): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowInsets = (getSystemService(Context.WINDOW_SERVICE) as WindowManager)
            .currentWindowMetrics
            .windowInsets
        return windowInsets.isVisible(WindowInsets.Type.navigationBars())
    } else {
        val display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val realMetrics = android.util.DisplayMetrics()
        display.getRealMetrics(realMetrics)

        val metrics = android.util.DisplayMetrics()
        display.getMetrics(metrics)

        return realMetrics.heightPixels > metrics.heightPixels || realMetrics.widthPixels > metrics.widthPixels
    }
}

@Composable
fun ZekirScreenBottomAppBar(
    category: CategoryModel,
    onCopyIconClicked: () -> Unit
) {
    val modifier = LocalModifier.current
    val viewmodel = LocalViewModel.current
    val context = LocalContext.current

    BottomAppBar(
        modifier = modifier
            .padding(bottom = if (context.hasNavigationBar()) 40.dp else 0.dp),
        cutoutShape = RoundedCornerShape(50),
        backgroundColor = Color.White,
        contentPadding = PaddingValues(0.dp),
        content = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                elevation = 0.dp
            ) {
                BottomNavigationItem(
                    modifier = Modifier.padding(end = 30.dp),
                    selected = false,
                    onClick = onCopyIconClicked,
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_copy),
                            tint = MaterialTheme.colorScheme.onPrimary,
                            contentDescription = "Copy"
                        )
                    },
                )
                BottomNavigationItem(
                    modifier = Modifier.padding(start = 30.dp),
                    selected = false,
                    onClick = {
                        viewmodel.updateCategory(
                            categoryId = category.id,
                            isFavorite = !category.isFavorite
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = if (category.isFavorite) Icons.Default.Favorite
                            else Icons.Default.FavoriteBorder,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            contentDescription = "Copy"
                        )
                    },
                )
            }
        }
    )
}