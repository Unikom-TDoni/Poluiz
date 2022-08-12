package com.lnco.poluiz.feature.domain.leaderboard

import android.net.Uri

data class UpdateScoreRequest(
    val username: String?,
    val imageUrl: Uri?,
    val score: Int,
)