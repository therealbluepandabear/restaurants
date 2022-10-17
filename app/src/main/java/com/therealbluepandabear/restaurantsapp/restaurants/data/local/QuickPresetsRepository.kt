package com.therealbluepandabear.restaurantsapp.restaurants.data.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuickPresetsRepository @Inject constructor(
    private val dao: QuickPresetsDao
) {
    suspend fun getQuickPresets(): List<QuickPreset> {
        return withContext(Dispatchers.IO) {
            return@withContext dao.getAll()
        }
    }
}