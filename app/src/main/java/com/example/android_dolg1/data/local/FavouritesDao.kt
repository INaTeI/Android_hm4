package com.example.android_dolg1.data.local

import androidx.room.Dao
import androidx.room.Query

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy


@Dao
interface FavouritesDao {
    @Query("SELECT * FROM favourites")
    suspend fun getAll(): List<FavouriteCountryEntity>

    @Query("SELECT * FROM favourites WHERE code = :code LIMIT 1")
    suspend fun getByCode(code: String): FavouriteCountryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(country: FavouriteCountryEntity)

    @Delete
    suspend fun delete(country: FavouriteCountryEntity)
}