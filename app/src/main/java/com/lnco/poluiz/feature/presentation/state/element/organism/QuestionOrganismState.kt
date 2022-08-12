package com.lnco.poluiz.feature.presentation.state.element.organism

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.lnco.poluiz.feature.domain.ui.QuestionType

@Stable
class QuestionOrganismState(
    command: String,
    question: String,
    imageUrl: String,
    questionType: QuestionType
) {
    var command by mutableStateOf(command)
        private set

    var question by mutableStateOf(question)
        private set

    var imageUrl by mutableStateOf(imageUrl)
        private set

    var questionType by mutableStateOf(questionType)
        private set

    fun change(command: String, question: String, imageUrl: String) {
        this.command = command
        questionType = when (command) {
            "dfsdf" -> QuestionType.Image
            "sdfsdf" -> QuestionType.Sound
            else -> QuestionType.Text
        }
        this.question = question
        this.imageUrl = imageUrl
    }
}