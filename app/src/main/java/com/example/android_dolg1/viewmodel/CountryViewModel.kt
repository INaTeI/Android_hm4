package com.example.android_dolg1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_dolg1.data.repository.CountryRepositoryImpl
import com.example.android_dolg1.domain.model.Country
import com.example.android_dolg1.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import com.example.android_dolg1.data.local.FavouritesRepository
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val repository: CountryRepositoryImpl,
    private val favouritesRepository: FavouritesRepository
) : ViewModel() {




    var countriesState by mutableStateOf<UiState<List<Country>>>(UiState.Loading)
        private set

    var detailState by mutableStateOf<UiState<Country>>(UiState.Loading)
        private set

    val favourites = mutableStateListOf<Country>()


    init {
        viewModelScope.launch {
            favourites.addAll(favouritesRepository.getFavourites())
        }
    }
    fun toggleFavourite(country: Country) {
        viewModelScope.launch {
            if (favourites.contains(country)) {
                favourites.remove(country)
                favouritesRepository.remove(country)
            } else {
                favourites.add(country)
                favouritesRepository.add(country)
            }
        }
    }

    fun loadCountries() {
        viewModelScope.launch {
            countriesState = UiState.Loading
            try {
                val list = repository.getCountries()
                countriesState = if (list.isEmpty()) UiState.Empty else UiState.Success(list)
            } catch (e: Exception) {
                countriesState = UiState.Error("Ошибка загрузки")
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
                detailState = UiState.Error("Ошибка загрузки")
            }
        }
    }
}