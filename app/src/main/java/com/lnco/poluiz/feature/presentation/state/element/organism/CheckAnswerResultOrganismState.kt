package com.lnco.poluiz.feature.presentation.state.element.organism

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.lnco.poluiz.feature.presentation.theme.*

@Stable
class CheckAnswerResultOrganismState {
    var answerDescText by mutableStateOf(String())
        private set

    var correctAnswerText by mutableStateOf(String())
        private set

    var backgroundColor by mutableStateOf(Color.Green)
        private set

    var textColor by mutableStateOf(antique_white)
        private set

    var isAnswerCorrect by mutableStateOf(false)
        private set

    var isNeedToShow by mutableStateOf(false)
        private set

    fun showResult(correctAnswer: String, isAnswerCorrect: Boolean) {
        isNeedToShow = true
        this.isAnswerCorrect = isAnswerCorrect
        if (isAnswerCorrect) {
            answerDescText = "Great!"
            textColor = highlighter_green
            backgroundColor = honeydew
        } else {
            textColor = highlighter_red
            backgroundColor = light_red
            answerDescText = "Wrong Answer"
            correctAnswerText = correctAnswer
        }
    }

    fun hideResult() {
        isNeedToShow = false
    }
}