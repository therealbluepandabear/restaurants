/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.therealbluepandabear.restaurantsapp.restaurants.presentation.new

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.therealbluepandabear.restaurantsapp.restaurants.data.local.quickpresets.QuickPresetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewScreenViewModel @Inject constructor(
    private val repository: QuickPresetsRepository
): ViewModel() {

    private val _state = mutableStateOf(
        NewScreenState(
            quickPresets = emptyList(),
        )
    )
    val state: State<NewScreenState>
        get() = _state

    init {
        getQuickPresets()
    }

    private fun getQuickPresets() {
        viewModelScope.launch {
            val quickPresets = repository.getQuickPresets()

            _state.value = _state.value.copy(
                quickPresets = quickPresets,
            )
        }
    }
}