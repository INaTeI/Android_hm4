package com.example.android_dolg1.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.android_dolg1.domain.model.Country

@Composable
fun CountryCard(
    country: Country,
    isFavourite: Boolean,
    onClick: () -> Unit,
    onFavourite: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Image(
                painter = rememberAsyncImagePainter(country.flag),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(country.name, style = MaterialTheme.typography.titleMedium)
                Text(country.region)
            }
            IconButton(onClick = onFavourite) {
                Icon(
                    imageVector =
                        if (isFavourite) Icons.Default.Favorite
                        else Icons.Default.FavoriteBorder,
                    contentDescription = "Favourite"
                )
            }
        }
    }
}