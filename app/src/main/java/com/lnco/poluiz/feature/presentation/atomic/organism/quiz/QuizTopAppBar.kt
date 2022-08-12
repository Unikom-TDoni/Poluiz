package com.lnco.poluiz.feature.presentation.atomic.organism

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.lnco.poluiz.feature.presentation.atomic.molecule.PlayerHealth
import com.lnco.poluiz.feature.presentation.state.element.organism.QuizTopAppBarOrganismState
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme

@Composable
fun QuizTopAppBarOrganism(
    state: QuizTopAppBarOrganismState
) = TopAppBar(
    title = {},
    navigationIcon = {
        IconButton(onClick = state.onNavigationClick) {
            Icon(Icons.Filled.ArrowBack, "back_icon")
        }
    },
    actions = {
        PlayerHealth(state.playerHealthState)
    }
)

@Preview
@Composable
private fun Preview() =
    PoluizTheme {
        QuizTopAppBarOrganism(QuizTopAppBarOrganismState(3) {})
    }
