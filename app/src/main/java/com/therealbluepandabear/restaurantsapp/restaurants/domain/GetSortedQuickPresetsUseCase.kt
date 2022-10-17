package com.therealbluepandabear.restaurantsapp.restaurants.domain

import com.therealbluepandabear.restaurantsapp.restaurants.data.local.QuickPreset
import com.therealbluepandabear.restaurantsapp.restaurants.data.local.QuickPresetsRepository
import javax.inject.Inject

class GetSortedQuickPresetsUseCase @Inject constructor(
    private val repository: QuickPresetsRepository
) {
    suspend operator fun invoke(): List<QuickPreset> {
        return repository.getQuickPresets()
    }
}