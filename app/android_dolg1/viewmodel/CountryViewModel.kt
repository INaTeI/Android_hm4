package com.example.android_dolg1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_dolg1.data.repository.CountryRepositoryImpl
import com.example.android_dolg1.domain.model.Country
import com.example.android_dolg1.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CountryViewModel @Inject constructor(
    private val repository: CountryRepositoryImpl
) : ViewModel() {


    var countriesState by mutableStateOf<UiState<List<Country>>>(UiState.Loading)
        private set

    var detailState by mutableStateOf<UiState<Country>>(UiState.Loading)
        private set


    fun loadCountries() {

        viewModelScope.launch {

            countriesState = UiState.Loading

            try {

                val countries = repository.getCountries()
                countriesState = UiState.Success(countries)

            } catch (e: Exception) {

                countriesState = UiState.Error("Failed to load countries")

            }
        }
    }


    fun loadCountry(code: String) {

        viewModelScope.launch {

            detailState = UiState.Loading

            try {

                val country = repository.getCountry(code)
                detailState = UiState.Success(country)

            } catch (e: Exception) {

                detailState = UiState.Error("Failed to load country")
            }
        }
    }
}