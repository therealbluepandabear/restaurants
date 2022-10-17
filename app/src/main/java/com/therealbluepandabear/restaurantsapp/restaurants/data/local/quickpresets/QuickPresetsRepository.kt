package com.therealbluepandabear.restaurantsapp.restaurants.data.local.quickpresets

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuickPresetsRepository @Inject constructor(private val restaurantsDao: QuickPresetsDao) {
    suspend fun getQuickPresets(): List<QuickPreset> {
        return withContext(Dispatchers.IO) {
            return@withContext restaurantsDao.getAll()
        }
    }
}