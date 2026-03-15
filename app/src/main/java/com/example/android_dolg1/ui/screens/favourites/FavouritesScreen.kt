package com.example.android_dolg1.ui.screens.favouritesScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import com.example.android_dolg1.components.CountryCard
import com.example.android_dolg1.viewmodel.CountryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(
    vm: CountryViewModel,
    onCountryClick: (String) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("избранное") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->

        if (vm.favourites.isEmpty()) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Нет избранных стран")
            }
        } else {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(vm.favourites) { country ->
                    CountryCard(
                        country = country,
                        isFavourite = true,
                        onClick = { onCountryClick(country.code) },
                        onFavourite = { vm.toggleFavourite(country) }
                    )
                }
            }
        }
    }
}