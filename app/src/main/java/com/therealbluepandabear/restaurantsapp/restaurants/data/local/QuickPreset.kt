package com.therealbluepandabear.restaurantsapp.restaurants.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quickpresets")
data class QuickPreset(
    @ColumnInfo(name = "qp_width")
    val width: Int,
    @ColumnInfo(name = "qp_height")
    val height: Int
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}