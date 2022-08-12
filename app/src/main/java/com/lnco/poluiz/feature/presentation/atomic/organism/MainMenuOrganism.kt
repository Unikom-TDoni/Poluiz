package com.lnco.poluiz.feature.presentation.atomic.organism

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.layoutId
import com.google.common.collect.ImmutableCollection
import com.google.common.collect.ImmutableList
import com.lnco.poluiz.feature.presentation.atomic.molecule.button.MenuButton
import com.lnco.poluiz.feature.presentation.state.element.molecule.button.ButtonState
import com.lnco.poluiz.feature.presentation.state.element.molecule.button.MenuButtonState
import com.lnco.poluiz.feature.presentation.theme.Id
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun MainMenuButtonOrganism(
    id: String,
    states: ImmutableCollection<MenuButtonState>,
) {
    Column(
        modifier = Modifier.layoutId(id),
    ) {
        for (item in states) {
            MenuButton(state = item)
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.normal100))
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MainMenuButtonOrganism(
        Id.MainMenuButtonOrganism, ImmutableList.of(
            MenuButtonState("Start") { },
            MenuButtonState("Leaderboard") { },
            MenuButtonState("Exit") { },
        )
    )
}