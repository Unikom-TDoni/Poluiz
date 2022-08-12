package com.lnco.poluiz.feature.presentation.atomic.organism

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.lnco.poluiz.feature.domain.ui.QuestionType
import com.lnco.poluiz.feature.presentation.state.element.organism.QuestionOrganismState
import com.lnco.poluiz.feature.presentation.theme.Id
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun QuestionOrganism(
    id: String,
    state: QuestionOrganismState
) {
    Column(
        modifier = Modifier.layoutId(id)
    ) {
        Text(
            text = state.command,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.normal100))
        when (state.questionType) {
            QuestionType.Text -> Text(
                text = state.question,
                style = MaterialTheme.typography.body1,
            )
            QuestionType.Image -> {}
            QuestionType.Sound -> {}
            else -> ""
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val organismState = QuestionOrganismState(
        "Penuhi kalimat berikut",
        "Budi Adalah------",
        "",
        QuestionType.Text
    )

    PoluizTheme {
        QuestionOrganism(
            Id.QuestionOrganism,
            organismState
        )
    }
}
