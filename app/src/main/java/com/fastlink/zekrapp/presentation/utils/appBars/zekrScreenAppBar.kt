package com.fastlink.zekrapp.presentation.utils.appBars

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.fastlink.zekrapp.LocalNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZekirScreenAppBar(title: String) {
    val navController = LocalNavController.current
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        actions = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 20.dp, top = 2.dp)
                    .clickable {
                        navController.navigateUp()
                    },
                tint = MaterialTheme.colorScheme.onPrimary
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.MoreVert, contentDescription = null,
                modifier = Modifier
                    .padding(start = 15.dp, top = 5.dp)
                    .clickable {},
                tint = MaterialTheme.colorScheme.onPrimary
            )
        },
        modifier = Modifier.clip(
            RoundedCornerShape(
                bottomStart = 20.dp,
                bottomEnd = 20.dp
            ),
        ),
        title = {
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 1.dp),
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    )
}