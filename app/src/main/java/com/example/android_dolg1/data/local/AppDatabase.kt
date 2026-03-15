package com.example.android_dolg1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavouriteCountryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favouritesDao(): FavouritesDao
}