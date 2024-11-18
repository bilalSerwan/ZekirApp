package com.fastlink.zekrapp.ui.zekirScreen.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomSnackBar(title: String, modifier: Modifier = Modifier) {
    Snackbar(
        modifier = modifier
            .padding(5.dp)
            .padding(bottom = 15.dp)
            .widthIn(max = 130.dp),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colorScheme.primary.copy(0.5f),
        shape = RoundedCornerShape(10.dp),
        contentColor = MaterialTheme.colorScheme.onPrimary,
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}