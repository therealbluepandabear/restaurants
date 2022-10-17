package com.therealbluepandabear.restaurantsapp.restaurants.data.di

import android.content.Context
import androidx.room.Room
import com.therealbluepandabear.restaurantsapp.restaurants.data.local.RestaurantsDao
import com.therealbluepandabear.restaurantsapp.restaurants.data.local.RestaurantsDb
import com.therealbluepandabear.restaurantsapp.restaurants.data.local.quickpresets.QuickPresetsDao
import com.therealbluepandabear.restaurantsapp.restaurants.data.local.quickpresets.QuickPresetsDb
import com.therealbluepandabear.restaurantsapp.restaurants.data.remote.RestaurantsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QuickPresetsModule {
    @Provides
    fun provideRoomDao(database: QuickPresetsDb): QuickPresetsDao {
        return database.dao
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext appContext: Context
    ): QuickPresetsDb {
        return Room.databaseBuilder(
            appContext,
            QuickPresetsDb::class.java,
            "quickpresets_database"
        ).fallbackToDestructiveMigration().build()
    }
}