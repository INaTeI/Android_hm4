package com.example.android_dolg1.di


import android.content.Context
import androidx.room.Room
import com.example.android_dolg1.data.local.AppDatabase
import com.example.android_dolg1.data.local.FavouritesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "countries_db"
        ).build()
    }

    @Provides
    fun provideFavouritesDao(db: AppDatabase): FavouritesDao {
        return db.favouritesDao()
    }
}