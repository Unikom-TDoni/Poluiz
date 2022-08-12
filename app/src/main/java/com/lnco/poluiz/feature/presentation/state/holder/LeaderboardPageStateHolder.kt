package com.lnco.poluiz.feature.presentation.state.holder

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import com.lnco.poluiz.feature.domain.leaderboard.Leaderboard
import com.lnco.poluiz.feature.presentation.state.element.organism.LeaderboardOrganismState
import com.lnco.poluiz.module.compose.PageStateHolder
import kotlinx.coroutines.CoroutineScope

@Stable
class LeaderboardPageStateHolder(
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    val onNavigationClick: () -> Unit
) : PageStateHolder(scaffoldState, coroutineScope) {

    var leaderboardOrganismState by mutableStateOf(LeaderboardOrganismState(listOf()))
        private set

    fun init(leaderboards: Collection<Leaderboard>) {
        leaderboardOrganismState = LeaderboardOrganismState(leaderboards.toList())
    }


}


@Composable
fun rememberLeaderboardPageStateHolder(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onNavigationClick: () -> Unit
) = remember(scaffoldState, coroutineScope) {
    LeaderboardPageStateHolder(scaffoldState, coroutineScope, onNavigationClick)
}