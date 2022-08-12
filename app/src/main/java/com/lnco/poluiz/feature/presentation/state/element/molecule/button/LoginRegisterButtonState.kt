package com.lnco.poluiz.feature.presentation.state.element.molecule.button

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class LoginRegisterButtonState(
    text: String,
    onClick: () -> Unit,
) : ButtonState(text) {

    override val onClick: () -> Unit = {
        onClick()
    }
}