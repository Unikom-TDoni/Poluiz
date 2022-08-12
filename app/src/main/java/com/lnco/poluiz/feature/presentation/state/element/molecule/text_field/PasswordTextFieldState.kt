package com.lnco.poluiz.feature.presentation.state.element.molecule.text_field

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusManager

@Stable
class PasswordTextFieldState(
    label: String,
    focusManager: FocusManager
) : TextFieldState(label, focusManager) {

    var isPasswordVisible by mutableStateOf(false)
        private set

    fun togglePasswordState() {
        isPasswordVisible = !isPasswordVisible
    }
}