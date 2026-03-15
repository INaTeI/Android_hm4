package com.example.android_dolg1.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.android_dolg1.ui.state.UiState
import com.example.android_dolg1.viewmodel.CountryViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDetailScreen(
    code: String,
    vm: CountryViewModel,
    onBack: () -> Unit
) {
    val country = (vm.countriesState as? UiState.Success)?.data?.firstOrNull { it.code == code }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("О стране") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (country == null) {
            Text("Country not found", modifier = Modifier.padding(paddingValues))
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(country.flag),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )

                Spacer(Modifier.height(16.dp))

                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(country.name, style = MaterialTheme.typography.headlineSmall)
                        Spacer(Modifier.height(8.dp))
                        Text("Region: ${country.region}")
                        Text("Capital: ${country.capital}")
                        Text("Population: ${country.population}")
                    }
                }
            }
        }
    }
}