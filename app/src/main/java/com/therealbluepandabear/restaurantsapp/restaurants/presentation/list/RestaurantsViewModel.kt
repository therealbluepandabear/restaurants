package com.therealbluepandabear.restaurantsapp.restaurants.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.therealbluepandabear.restaurantsapp.restaurants.domain.GetInitialRestaurantsUseCase
import com.therealbluepandabear.restaurantsapp.restaurants.domain.ToggleRestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor(
    private val getRestaurantsUseCase: GetInitialRestaurantsUseCase,
    private val toggleRestaurantsUseCase: ToggleRestaurantUseCase
): ViewModel() {

    private val _state = mutableStateOf(
        RestaurantsScreenState(
            restaurants = emptyList(),
            isLoading = true
        )
    )
    val state: State<RestaurantsScreenState>
        get() = _state

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _state.value = _state.value.copy(
            error = exception.message,
            isLoading = false
        )
    }

    init {
        getRestaurants()
    }

    private fun getRestaurants() {
        viewModelScope.launch(errorHandler) {
            val restaurants = getRestaurantsUseCase()
            _state.value = _state.value.copy(
                restaurants = restaurants,
                isLoading = false
            )
        }
    }


    fun toggleFavorite(id: Int, oldValue: Boolean) {
        viewModelScope.launch(errorHandler) {
            val updatedRestaurants = toggleRestaurantsUseCase(id, oldValue)
            _state.value = _state.value.copy(
                restaurants = updatedRestaurants
            )
        }
    }
}