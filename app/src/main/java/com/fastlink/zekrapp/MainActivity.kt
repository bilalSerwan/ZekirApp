package com.fastlink.zekrapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fastlink.zekrapp.navigation.Navigation
import com.fastlink.zekrapp.ui.theme.ZekrAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController: NavHostController = rememberNavController()
            ZekrAppTheme {
                CompositionLocalProvider {
                    Navigation(
                        navController = navController,
                    )
                }
            }
        }
    }
}