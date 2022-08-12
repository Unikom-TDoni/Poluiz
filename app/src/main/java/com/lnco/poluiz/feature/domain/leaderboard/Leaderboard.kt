package com.lnco.poluiz.feature.domain.leaderboard

data class Leaderboard(
    val username: String = String(),
    val imageUrl: String = String(),
    val score: Int = 0
)