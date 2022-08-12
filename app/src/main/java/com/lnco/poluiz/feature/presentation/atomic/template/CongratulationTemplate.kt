package com.lnco.poluiz.feature.presentation.atomic.template

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.lnco.poluiz.feature.presentation.atomic.molecule.button.MenuButton
import com.lnco.poluiz.feature.presentation.atomic.organism.quiz.CongratulationOrganism
import com.lnco.poluiz.feature.presentation.state.holder.CongratulationPageStateHolder
import com.lnco.poluiz.feature.presentation.state.holder.rememberCongratulationPageStateHolder
import com.lnco.poluiz.feature.presentation.theme.Id
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun CongratulationTemplate(
    pageStateHolder: CongratulationPageStateHolder
) {
    val constraints = generateConstraints()
    PoluizTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = pageStateHolder.scaffoldState,
        ) {
            ConstraintLayout(
                constraints,
                Modifier.fillMaxSize()
                    .padding(
                        end = MaterialTheme.dimens.normal100,
                        start = MaterialTheme.dimens.normal100,
                        bottom = MaterialTheme.dimens.normal100
                    ),
            ) {
                CongratulationOrganism(Id.CongratulationOrganism, pageStateHolder.userScore)
                MenuButton(Id.ContinueButton, pageStateHolder.menuButtonState)
            }
        }
    }
}


private fun generateConstraints() =
    ConstraintSet {
        val congratulationOrganism = createRefFor(Id.CongratulationOrganism)
        val continueButton = createRefFor(Id.ContinueButton)

        constrain(congratulationOrganism) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }

        constrain(continueButton) {
            bottom.linkTo(parent.bottom)
        }
    }

@Preview
@Composable
private fun Preview() =
    PoluizTheme {
        CongratulationTemplate(rememberCongratulationPageStateHolder(totalScore = 100) {})
    }