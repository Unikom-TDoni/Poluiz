package com.lnco.poluiz.feature.presentation.atomic.organism.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.lnco.poluiz.feature.presentation.state.element.organism.CheckAnswerResultOrganismState
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun CheckAnswerResultOrganism(
    state: CheckAnswerResultOrganismState
) {
    if (state.isNeedToShow) {
        Column(
            modifier = Modifier.background(state.backgroundColor)
                .padding(
                    top = MaterialTheme.dimens.normal150,
                    end = MaterialTheme.dimens.normal100,
                    start = MaterialTheme.dimens.normal100,
                    bottom = MaterialTheme.dimens.extraLarge137
                )
                .fillMaxWidth()
        ) {
            Text(
                text = state.answerDescText,
                color = state.textColor,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
            )
            if (!state.isAnswerCorrect) {
                Text(
                    text = "The Correct Answer Is : ",
                    color = state.textColor,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = state.correctAnswerText,
                    color = state.textColor,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() =
    PoluizTheme {
        CheckAnswerResultOrganism(CheckAnswerResultOrganismState())
    }
