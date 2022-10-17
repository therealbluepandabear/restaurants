package com.therealbluepandabear.restaurantsapp.restaurants.presentation.list

import androidx.annotation.StringRes
import com.therealbluepandabear.restaurantsapp.restaurants.data.local.QuickPreset

data class NewScreenState(
    @StringRes val nameError: Int? = null,
    @StringRes val widthError: Int? = null,
    @StringRes val heightError: Int? = null,
    val name: String = "",
    val width: String = "",
    val height: String = "",
    val quickPresets: List<QuickPreset>
)