package com.lnco.poluiz.feature.presentation.state.element.organism

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.lnco.poluiz.feature.presentation.state.element.molecule.PlayerHealthState

@Stable
class QuizTopAppBarOrganismState(
    life: Int,
    val onNavigationClick: () -> Unit,
) {
    val playerHealthState by mutableStateOf(PlayerHealthState(life))

    fun updateLife(life: Int) =
        playerHealthState.updateLife(life)
}