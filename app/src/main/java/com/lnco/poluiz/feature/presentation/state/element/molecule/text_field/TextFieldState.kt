package com.lnco.poluiz.feature.presentation.state.element.molecule.text_field

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusManager

@Stable
open class TextFieldState(
    val label: String,
    val focusManager: FocusManager
) {
    var text by mutableStateOf(String())
        protected set

    var isError by mutableStateOf(false)
        protected set

    var errorMessage by mutableStateOf(String())
        private set

    fun onError(errorMessage: String) {
        isError = true
        this.errorMessage = errorMessage
    }

    fun onValueChange(text: String) {
        if (isError) isError = false
        this.text = text
    }
}