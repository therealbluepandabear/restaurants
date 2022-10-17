package com.therealbluepandabear.restaurantsapp.restaurants.presentation.list

import com.therealbluepandabear.restaurantsapp.restaurants.domain.Restaurant

data class RestaurantsScreenState(
    val restaurants: List<Restaurant>,
    val isLoading: Boolean,
    val error: String? = null
)