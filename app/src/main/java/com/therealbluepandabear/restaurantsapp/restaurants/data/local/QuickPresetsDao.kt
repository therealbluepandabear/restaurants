package com.therealbluepandabear.restaurantsapp.restaurants.data.local

import androidx.room.*

@Dao
interface QuickPresetsDao {
    @Query("SELECT * FROM quickpresets")
    suspend fun getAll(): List<QuickPreset>

    @Insert
    suspend fun add(quickPreset: QuickPreset)
}