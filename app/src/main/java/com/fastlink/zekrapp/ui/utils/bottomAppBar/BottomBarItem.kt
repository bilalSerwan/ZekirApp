package com.fastlink.zekrapp.ui.utils.bottomAppBar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomAppBarItem(
    icon: ImageVector,
    text: String?,
    selected: Boolean,
    onClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(
                onClick = onClicked,
                role = Role.Button,
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "$text icon",
            modifier = Modifier.size(24.dp),
            tint = if (selected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.inversePrimary,
        )
        if (text != null) Text(
            text = text, color = if (selected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.inversePrimary,
            fontSize = 15.sp
        )
    }
}

