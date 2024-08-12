package com.fastlink.zekrapp


import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.ZekrAppTheme
import com.fastlink.zekrapp.presentation.navigation.Navigation
import com.fastlink.zekrapp.presentation.navigation.Screen
import com.fastlink.zekrapp.presentation.utils.appBars.AppBar
import com.fastlink.zekrapp.presentation.utils.bottomAppBar.BottomBar
import com.fastlink.zekrapp.viewModel.MainViewModel


val LocalNavController = compositionLocalOf<NavHostController> { error("error in navController") }
val LocalViewModel = compositionLocalOf<MainViewModel> { error("error in view model") }
val LocalModifier = compositionLocalOf<Modifier> { error("error in modifier") }
val LocalToast = compositionLocalOf<(String) -> Unit> { error("error in toast") }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZekrAppTheme {
                val toast: Toast = Toast.makeText(this, "", Toast.LENGTH_SHORT)
                val navigation = rememberNavController()
                val viewmodel: MainViewModel = viewModel()
                CompositionLocalProvider(
                    LocalNavController provides navigation,
                    LocalViewModel provides viewmodel,
                    LocalModifier provides Modifier,
                    LocalToast provides { message ->
                        showToast(toast, message)
                    }
                ) {
                    MyApp()
                }
            }
        }
    }

    private fun showToast(toast: Toast, message: String) {
        toast.setText(message)
        toast.setGravity(Gravity.BOTTOM, 0, 300)
        toast.show()
    }
}

@Composable
fun MyApp() {
    val navController = LocalNavController.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    if (navBackStackEntry?.destination?.route
        == "${Screen.ZekirScreen.route}/{categoryId}"
    ) {
        Navigation()
    } else Scaffold(
        topBar = {
            AppBar()
        },
        bottomBar = {
            BottomBar()
        },
        content = {
            LocalModifier.current.padding(it)
            Navigation()
        }
    )
}




