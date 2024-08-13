package com.fastlink.zekrapp


import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fastlink.zekrapp.ui.theme.ZekrAppTheme
import com.fastlink.zekrapp.presentation.navigation.Navigation
import com.fastlink.zekrapp.viewModel.MainViewModel


val LocalNavController = compositionLocalOf<NavHostController> { error("error in navController") }
val LocalViewModel = compositionLocalOf<MainViewModel> { error("error in view model") }
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
                    LocalToast provides { message ->
                        showToast(toast, message)
                    }
                ) {
                   Navigation()
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





