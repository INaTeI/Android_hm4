package com.example.android_dolg1.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.android_dolg1.domain.model.Country

@Composable
fun CountryCard(
    country: Country,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column {
            AsyncImage(
                model = country.flag,
                contentDescription = null,
                modifier = Modifier.height(100.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(country.name)
            Text("Capital: ${country.capital}")
        }
    }
}