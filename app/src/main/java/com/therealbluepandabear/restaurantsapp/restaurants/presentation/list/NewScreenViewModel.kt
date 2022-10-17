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

package com.therealbluepandabear.restaurantsapp.restaurants.presentation.list

import androidx.annotation.StringRes
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.therealbluepandabear.restaurantsapp.R
import com.therealbluepandabear.restaurantsapp.restaurants.data.local.QuickPresetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewScreenViewModel @Inject constructor(private val repository: QuickPresetsRepository) : ViewModel() {

    private val _state = mutableStateOf(
        NewScreenState(
            quickPresets = emptyList()
        )
    )

    private val textValidator = TextValidator()

    val state: State<NewScreenState>
        get() = _state

    val areInputsValid: Boolean
        get() = _state.value.nameError == null && _state.value.widthError == null && _state.value.heightError == null

    private inner class TextValidator {

        @StringRes
        private fun validateSize(input: String, @StringRes returnWhenEmpty: Int, @StringRes returnWhenOutOfBounds: Int, skipIsBlankCheck: Boolean): Int? {
            return when {
                input.isBlank() && !skipIsBlankCheck -> returnWhenEmpty
                (input.toIntOrNull() ?: 0) !in SIZE_MIN..SIZE_MAX && input.isNotBlank() -> returnWhenOutOfBounds
                else -> null
            }
        }

        fun validateName(input: String, skipIsBlankCheck: Boolean = false) {
            _state.value = _state.value.copy(
                nameError = if (input.isBlank() && !skipIsBlankCheck) R.string.exception_invalid_project_name else null
            )
        }

        fun validateWidth(input: String, skipIsBlankCheck: Boolean = false) {
            _state.value = _state.value.copy(
                widthError = validateSize(
                    input,
                    returnWhenEmpty = R.string.exception_empty_width,
                    returnWhenOutOfBounds = R.string.exception_invalid_width,
                    skipIsBlankCheck = skipIsBlankCheck
                )
            )
        }

        fun validateHeight(input: String, skipIsBlankCheck: Boolean = false) {
            _state.value = _state.value.copy(
                heightError = validateSize(
                    input,
                    returnWhenEmpty = R.string.exception_empty_height,
                    returnWhenOutOfBounds = R.string.exception_invalid_height,
                    skipIsBlankCheck = skipIsBlankCheck
                )
            )
        }
    }

    init {
        getQuickPresets()
    }

    private fun getQuickPresets() {
//        viewModelScope.launch {
//            val quickPresets = repository.getQuickPresets()
//
//            _state.value = _state.value.copy(
//                quickPresets = quickPresets
//            )
//        }
    }

    private fun String.digitsOnly(): String {
        val regex = Regex("[^0-9]")

        return regex.replace(this, "")
    }

    fun updateName(input: String) {
        _state.value = _state.value.copy(
            name = input
        )

        textValidator.validateName(_state.value.name, true)
    }

    fun updateWidth(input: String) {
        _state.value = _state.value.copy(
            width = input.digitsOnly()
        )

        textValidator.validateWidth(_state.value.width, true)
    }

    fun updateHeight(input: String) {
        _state.value = _state.value.copy(
            height = input.digitsOnly()
        )

        textValidator.validateHeight(_state.value.height, true)
    }

    fun validateAll() {
        textValidator.validateName(_state.value.name)
        textValidator.validateWidth(_state.value.width)
        textValidator.validateHeight(_state.value.height)
    }

    companion object {
        private const val SIZE_MIN: Int = 1
        private const val SIZE_MAX: Int = 10_000
    }
}