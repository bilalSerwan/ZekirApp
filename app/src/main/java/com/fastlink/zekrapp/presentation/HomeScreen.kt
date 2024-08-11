package com.fastlink.zekrapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fastlink.zekrapp.LocalModifier
import com.fastlink.zekrapp.LocalViewModel
import com.fastlink.zekrapp.presentation.utils.CategoryItem

@Composable
fun HomeScreen() {
    val viewModel = LocalViewModel.current
    val modifier = LocalModifier.current
    val categories = viewModel.categories.value
    LazyColumn(
        modifier = modifier
            .padding(
                top = 70.dp,
                bottom = 100.dp,
            )
            .background(MaterialTheme.colorScheme.background)
    ) {
        items(categories) { category ->
            CategoryItem(category = category)
        }
        item {
            Spacer(
                modifier = Modifier
                    .size(35.dp)
                    .background(color = MaterialTheme.colorScheme.background)
            )
        }
    }
}