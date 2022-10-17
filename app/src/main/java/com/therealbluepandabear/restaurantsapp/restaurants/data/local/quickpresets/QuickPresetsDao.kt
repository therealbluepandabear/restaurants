package com.therealbluepandabear.restaurantsapp.restaurants.data.local.quickpresets

import androidx.room.*

@Dao
interface QuickPresetsDao {
    @Query("SELECT * FROM quickpresets")
    suspend fun getAll(): List<QuickPreset>
}