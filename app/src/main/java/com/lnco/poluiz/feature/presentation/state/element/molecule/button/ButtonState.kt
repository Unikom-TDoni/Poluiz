package com.lnco.poluiz.feature.presentation.state.element.molecule.button

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

abstract class ButtonState(
    text: String,
) {
    var text by mutableStateOf(text)
        protected set

    abstract val onClick: () -> Unit
}