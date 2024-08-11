package com.fastlink.zekrapp.presentation.utils.bottomAppBar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role

@Composable
fun BottomAppBarItem(
    icon: ImageVector,
    text: String,
    selected: Boolean,
    onClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(
                onClick = onClicked,
                role = Role.Button,
            )
            .focusProperties { canFocus = false },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "$text icon",
            tint = if (selected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.inversePrimary,
        )
        Text(
            text = text, color = if (selected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.inversePrimary
        )
    }
}

