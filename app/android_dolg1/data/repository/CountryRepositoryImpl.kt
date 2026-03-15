package com.example.android_dolg1.data.repository

import com.example.android_dolg1.data.api.CountriesApi
import com.example.android_dolg1.domain.model.Country
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val api: CountriesApi
) {

    suspend fun getCountries(): List<Country> {
        return api.getCountries().map {
            Country(
                name = it.name.common,
                code = it.cca2,
                capital = it.capital?.firstOrNull() ?: "Unknown",
                region = it.region ?: "",
                population = it.population ?: 0,
                flag = it.flags.png
            )
        }
    }

    suspend fun getCountry(code: String): Country {
        val dto = api.getCountry(code).first()

        return Country(
            name = dto.name.common,
            code = dto.cca2,
            capital = dto.capital?.firstOrNull() ?: "",
            region = dto.region ?: "",
            population = dto.population ?: 0,
            flag = dto.flags.png
        )
    }
}