package com.therealbluepandabear.restaurantsapp.restaurants.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [QuickPreset::class],
    version = 4,
    exportSchema = false
)
abstract class QuickPresetsDb : RoomDatabase() {
    abstract val dao: QuickPresetsDao
}
