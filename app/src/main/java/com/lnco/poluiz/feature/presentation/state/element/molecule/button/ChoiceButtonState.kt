package com.lnco.poluiz.feature.presentation.state.element.molecule.button

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.lnco.poluiz.feature.presentation.theme.antique_white
import com.lnco.poluiz.feature.presentation.theme.coral_reef

@Stable
class ChoiceButtonState(
    text: String,
    onClick: (String) -> Unit
) : ButtonState(text) {

    private val defaultColor = Color.White

    override val onClick: () -> Unit = {
        onClick(this.text)
        color = antique_white
    }

    var color by mutableStateOf(defaultColor)
        private set

    fun resetData(choice: String) {
        this.text = choice
        resetColor()
    }

    fun resetColor() {
        if (color != defaultColor)
            color = defaultColor
    }
}