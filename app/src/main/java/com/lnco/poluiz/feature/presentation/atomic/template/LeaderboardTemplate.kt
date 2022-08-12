package com.lnco.poluiz.feature.presentation.atomic.template

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.lnco.poluiz.feature.presentation.atomic.organism.leaderboard.LeaderboardListOrganism
import com.lnco.poluiz.feature.presentation.state.holder.LeaderboardPageStateHolder
import com.lnco.poluiz.feature.presentation.theme.Id
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun LeaderboardTemplate(
    pageStateHolder: LeaderboardPageStateHolder
) {
    val constraints = generateConstraints()
    PoluizTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = pageStateHolder.scaffoldState,
            topBar = {
                TopAppBar(
                    title = { Text("Leaderboard") },
                    navigationIcon = {
                        IconButton(onClick = pageStateHolder.onNavigationClick) {
                            Icon(Icons.Filled.ArrowBack, "back_icon")
                        }
                    }
                )
            },
        ) {
            ConstraintLayout(
                constraints,
                Modifier.fillMaxSize().padding(horizontal = MaterialTheme.dimens.normal100),
            ) {
                LeaderboardListOrganism(
                    Id.LeaderboardListOrganism,
                    pageStateHolder.leaderboardOrganismState
                )
            }
        }
    }
}


private fun generateConstraints() =
    ConstraintSet {
        val leaderboardList = createRefFor(Id.LeaderboardListOrganism)
        constrain(leaderboardList) {
            top.linkTo(parent.top)
        }
    }

