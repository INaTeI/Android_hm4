package com.example.android_dolg1.data.local

import com.example.android_dolg1.domain.model.Country
import javax.inject.Inject

class FavouritesRepository @Inject constructor(
    private val dao: FavouritesDao
) {

    suspend fun getFavourites(): List<Country> {
        return dao.getAll().map { it.toCountry() }
    }

    suspend fun add(country: Country) {
        dao.insert(FavouriteCountryEntity.fromCountry(country))
    }

    suspend fun remove(country: Country) {
        dao.delete(FavouriteCountryEntity.fromCountry(country))
    }
}