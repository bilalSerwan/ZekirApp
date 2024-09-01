package com.fastlink.zekrapp.ui.zekirScreen.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomSnackBar(title: String) {
    Snackbar(
        modifier = Modifier
            .size(130.dp, 40.dp)
            .wrapContentSize(align = Alignment.Center)
            .padding(5.dp)
            .widthIn(min = 40.dp),
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