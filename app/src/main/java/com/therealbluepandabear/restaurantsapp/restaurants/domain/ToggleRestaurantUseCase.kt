package com.therealbluepandabear.restaurantsapp.restaurants.domain

import com.therealbluepandabear.restaurantsapp.restaurants.data.local.RestaurantsRepository
import javax.inject.Inject

class ToggleRestaurantUseCase @Inject constructor (
    private val repository: RestaurantsRepository,
    private val getSortedRestaurantUseCase: GetSortedRestaurantsUseCase
) {
    suspend operator fun invoke(
        id: Int,
        oldValue: Boolean
    ): List<Restaurant> {
        val newFav = oldValue.not()
        repository.toggleFavoriteRestaurant(id, newFav)
        return getSortedRestaurantUseCase()
    }
}