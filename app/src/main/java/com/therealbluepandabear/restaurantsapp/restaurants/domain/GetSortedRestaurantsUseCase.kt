package com.therealbluepandabear.restaurantsapp.restaurants.domain

import com.therealbluepandabear.restaurantsapp.restaurants.data.local.RestaurantsRepository
import javax.inject.Inject

class GetSortedRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantsRepository
) {
    suspend operator fun invoke(): List<Restaurant> {
        return repository.getRestaurants()
            .sortedBy { it.title }
    }
}