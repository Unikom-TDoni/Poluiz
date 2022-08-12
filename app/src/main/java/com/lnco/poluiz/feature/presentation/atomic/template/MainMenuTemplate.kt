package com.lnco.poluiz.feature.presentation.atomic.template

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.lnco.poluiz.feature.presentation.atomic.organism.MainMenuButtonOrganism
import com.lnco.poluiz.feature.presentation.state.holder.MainMenuPageStateHolder
import com.lnco.poluiz.feature.presentation.theme.Id
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun MainMenuTemplate(
    pageStateHolder: MainMenuPageStateHolder
) {
    val constraints = generateConstraints()
    PoluizTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = pageStateHolder.scaffoldState
        ) {
            ConstraintLayout(
                constraints,
                Modifier.fillMaxSize()
                    .padding(horizontal = MaterialTheme.dimens.normal100),
            ) {
                MainMenuButtonOrganism(
                    Id.MainMenuButtonOrganism,
                    pageStateHolder.buttonStates
                )
            }
        }
    }
}

private fun generateConstraints() =
    ConstraintSet {
        val socialMediaButtons = createRefFor(Id.MainMenuButtonOrganism)
        constrain(socialMediaButtons) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }
    }