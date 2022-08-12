package com.lnco.poluiz.feature.presentation.state.holder

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.google.common.collect.ImmutableCollection
import com.google.common.collect.ImmutableList
import com.lnco.poluiz.core.navigation.PageNames
import com.lnco.poluiz.feature.presentation.state.element.molecule.button.MenuButtonState
import com.lnco.poluiz.module.compose.PageStateHolder
import kotlinx.coroutines.CoroutineScope

@Stable
class MainMenuPageStateHolder(
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    onMenuClick: (PageNames) -> Unit,
) : PageStateHolder(scaffoldState, coroutineScope) {
    val buttonStates: ImmutableCollection<MenuButtonState> = ImmutableList.of(
        MenuButtonState(
            text = "Start Quiz"
        ) {
            onMenuClick(PageNames.Quiz)
        },
        MenuButtonState(
            text = "Leaderboard",
        ) {
            onMenuClick(PageNames.Leaderboard)
        },
        MenuButtonState(
            text = "Logout",
        ) {
            onMenuClick(PageNames.Default)
        }
    )

}

@Composable
fun rememberMainMenuPageStateHolder(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onMenuClick: (pageNames: PageNames) -> Unit
) = remember(scaffoldState, onMenuClick) {
    MainMenuPageStateHolder(scaffoldState, coroutineScope, onMenuClick)
}