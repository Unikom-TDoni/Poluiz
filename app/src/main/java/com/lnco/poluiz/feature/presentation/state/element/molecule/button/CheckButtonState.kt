package com.lnco.poluiz.feature.presentation.state.element.molecule.button

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.lnco.poluiz.feature.presentation.theme.highlighter_green
import com.lnco.poluiz.feature.presentation.theme.highlighter_red

@Stable
class CheckButtonState(
    text: String,
    onClick: (Boolean) -> Unit,
) : ButtonState(text) {

    private val defaultText = text
    private val defaultColor = highlighter_green

    var enabled by mutableStateOf(false)
        private set

    var color by mutableStateOf(defaultColor)
        private set

    override val onClick = {
        onClick(this.text == "Continue" || this.text == "Ok")
    }

    fun resetState() {
        enabled = false
        text = defaultText
        color = defaultColor
    }

    fun enableButton() {
        enabled = true
    }

    fun changeStyle(isAnswerCorrect: Boolean) {
        if (!isAnswerCorrect) {
            text = "Ok"
            color = highlighter_red
        } else text = "Continue"
    }
}