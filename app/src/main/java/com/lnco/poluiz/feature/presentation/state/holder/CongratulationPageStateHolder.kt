package com.lnco.poluiz.feature.presentation.state.holder

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.lnco.poluiz.feature.presentation.state.element.molecule.button.MenuButtonState
import com.lnco.poluiz.module.compose.PageStateHolder
import kotlinx.coroutines.CoroutineScope

@Stable
class CongratulationPageStateHolder(
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    val userScore: Int,
    onContinueButtonClick: () -> Unit,
) : PageStateHolder(scaffoldState, coroutineScope) {
    val menuButtonState = MenuButtonState("Continue", onContinueButtonClick)
}

@Composable
fun rememberCongratulationPageStateHolder(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    totalScore: Int,
    onContinueButtonClick: () -> Unit,
) = remember(scaffoldState, coroutineScope) {
    CongratulationPageStateHolder(scaffoldState, coroutineScope, totalScore, onContinueButtonClick)
}