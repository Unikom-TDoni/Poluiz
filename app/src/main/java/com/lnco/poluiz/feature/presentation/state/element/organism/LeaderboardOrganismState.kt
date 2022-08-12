package com.lnco.poluiz.feature.presentation.state.element.organism

import androidx.compose.runtime.toMutableStateList
import com.lnco.poluiz.feature.domain.leaderboard.Leaderboard

class LeaderboardOrganismState(
    leaderboard: List<Leaderboard>
) {
    val leaderboard = leaderboard.toMutableStateList()
}