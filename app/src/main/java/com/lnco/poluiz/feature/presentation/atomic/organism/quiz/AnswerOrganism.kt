package com.lnco.poluiz.feature.presentation.atomic.organism

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.layoutId
import com.lnco.poluiz.feature.presentation.atomic.molecule.button.ChoiceButton
import com.lnco.poluiz.feature.presentation.state.element.organism.AnswerOrganismState
import com.lnco.poluiz.feature.presentation.theme.Id
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun AnswerOrganism(
    id: String,
    state: AnswerOrganismState
) =
    Column(
        modifier = Modifier.layoutId(id)
    ) {
        for (item in state.choiceButtonState) {
            ChoiceButton(item)
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.normal100))
        }
    }

@Preview
@Composable
private fun Preview() =
    PoluizTheme {
        AnswerOrganism(
            Id.AnswerOrganism,
            AnswerOrganismState(
                listOf(
                    "Why",
                    "Where",
                    "When",
                    "Who"
                )
            ) {}
        )
    }