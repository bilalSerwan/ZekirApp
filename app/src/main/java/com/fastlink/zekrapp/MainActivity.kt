package com.fastlink.zekrapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fastlink.zekrapp.appData.ZekirCategorySingleton
import com.fastlink.zekrapp.ui.theme.ZekrAppTheme
import com.fastlink.zekrapp.presentation.navigation.Navigation
import com.fastlink.zekrapp.viewModel.MainViewModel


val LocalNavController = compositionLocalOf<NavHostController> { error("error in navController") }
val LocalViewModel = compositionLocalOf<MainViewModel> { error("error in view model") }

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            ZekirCategorySingleton.getZekirCategoriesFromCSVFile(context)
            ZekrAppTheme {
                val navigation = rememberNavController()
                val viewmodel: MainViewModel = viewModel()
                CompositionLocalProvider(
                    LocalNavController provides navigation,
                    LocalViewModel provides viewmodel,
                ) {
                    Navigation()
                }
            }
        }
    }
}





