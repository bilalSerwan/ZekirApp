package com.fastlink.zekrapp.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fastlink.zekrapp.appData.model.ZekirCategoryModel
import com.fastlink.zekrapp.navigation.Screen

@Composable
fun ZekirCategoryCard(
    zekirCategory: ZekirCategoryModel,
    navController: NavController,
    onFavoriteIconClicked: () -> Unit
    ) {
    Surface(
        modifier = Modifier
            .heightIn(min = 70.dp, max = 150.dp)
            .padding(start = 20.dp, top = 15.dp, end = 20.dp)
            .clip(RoundedCornerShape(10.dp)),
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("${Screen.ZekirScreen.route}/${zekirCategory.id}")
                }
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(imageVector = if (zekirCategory.isFavorite) Icons.Default.Favorite
            else Icons.Default.FavoriteBorder,
                contentDescription = "Favorite",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .size(25.dp)
                    .clickable {
                      onFavoriteIconClicked()
                    })
            Text(
                text = zekirCategory.zekirCategoryTitle,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W400),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }
    }
}