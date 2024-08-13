package com.fastlink.zekrapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fastlink.zekrapp.LocalViewModel
import com.fastlink.zekrapp.presentation.utils.CategoryItem
import com.fastlink.zekrapp.presentation.utils.appBars.AppBar
import com.fastlink.zekrapp.presentation.utils.bottomAppBar.BottomAppBarForHomeAndFavoriteScreens

@Composable
fun HomeScreen() {
    val viewModel = LocalViewModel.current
    Scaffold(
        topBar = { AppBar() },
        bottomBar = { BottomAppBarForHomeAndFavoriteScreens() }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            ) {
                items(viewModel.categories.value) { category ->
                    CategoryItem(category = category)
                }
                item {
                    Spacer(modifier = Modifier.padding(10.dp))
                }
            }
        }
    }
}