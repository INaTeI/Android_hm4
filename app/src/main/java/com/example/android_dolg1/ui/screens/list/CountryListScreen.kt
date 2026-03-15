package com.example.android_dolg1.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

import com.example.android_dolg1.ui.state.UiState
import com.example.android_dolg1.viewmodel.CountryViewModel

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import com.example.android_dolg1.components.CountryCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryListScreen(
    navController: NavController,
    vm: CountryViewModel
) {
    val state = vm.countriesState

    LaunchedEffect(Unit) {
        vm.loadCountries()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Страны мира") },
                actions = {
                    IconButton(onClick = { navController.navigate("Favourites") }) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favourites")
                    }
                }
            )
        }
    ) { paddingValues ->

        when (state) {
            is UiState.Loading -> Box(
                Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }

            is UiState.Success -> LazyColumn(
                Modifier.fillMaxSize().padding(paddingValues)
            ) {
                items(state.data) { country ->
                    CountryCard(
                        country = country,
                        isFavourite = vm.favourites.contains(country),
                        onClick = { navController.navigate("detail/${country.code}") },
                        onFavourite = { vm.toggleFavourite(country) }
                    )
                }
            }

            is UiState.Error -> Text(
                text = (state as UiState.Error).message,
                modifier = Modifier.padding(paddingValues)
            )

            UiState.Empty -> Text("No countries", modifier = Modifier.padding(paddingValues))
        }
    }
}