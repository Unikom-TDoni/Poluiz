package com.lnco.poluiz.feature.presentation.state.element.organism

import androidx.compose.runtime.Stable
import androidx.compose.runtime.toMutableStateList
import com.lnco.poluiz.feature.presentation.state.element.molecule.button.ChoiceButtonState

@Stable
class AnswerOrganismState(
    choices: Collection<String>,
    onChoiceButtonClick: (String) -> Unit,
) {
    val choiceButtonState =
        choices.map { ChoiceButtonState(it, this.onChoiceButtonClick) }.toMutableStateList()

    private val onChoiceButtonClick: (selectedChoice: String) -> Unit = {
        choiceButtonState.forEach { item -> item.resetColor() }
        onChoiceButtonClick(it)
    }

    fun change(choices: Collection<String>) {
        if (choices.isEmpty()) return
        if (choiceButtonState.size == choices.size)
            for (i in choices.indices) choiceButtonState[i].resetData(choices.elementAt(i))
        else
            for (i in choices.indices) choiceButtonState.add(
                ChoiceButtonState(
                    choices.elementAt(i),
                    onChoiceButtonClick
                )
            )
    }
}