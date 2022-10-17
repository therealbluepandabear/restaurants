package com.therealbluepandabear.restaurantsapp.restaurants.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.therealbluepandabear.restaurantsapp.restaurants.domain.GetSortedQuickPresetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor(
    private val getQuickPresetsUseCase: GetSortedQuickPresetsUseCase,
): ViewModel() {

    private val _state = mutableStateOf(
        RestaurantsScreenState(
            restaurants = emptyList(),
            isLoading = true
        )
    )

    val state: State<RestaurantsScreenState>
        get() = _state

    init {
        getQuickPresets()
    }

    private fun getQuickPresets() {
        viewModelScope.launch {
            val restaurants = getQuickPresetsUseCase()
            _state.value = _state.value.copy(
                restaurants = restaurants,
                isLoading = false
            )
        }
    }
}