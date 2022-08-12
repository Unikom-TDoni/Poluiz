package com.lnco.poluiz.feature.presentation.atomic.organism.leaderboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.lnco.poluiz.R
import com.lnco.poluiz.feature.domain.leaderboard.Leaderboard
import com.lnco.poluiz.feature.presentation.state.element.organism.LeaderboardOrganismState
import com.lnco.poluiz.feature.presentation.theme.Id
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun LeaderboardListOrganism(
    id: String,
    state: LeaderboardOrganismState
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().layoutId(id)
    ) {
        itemsIndexed(items = state.leaderboard, itemContent = { index, item ->
            Row(
                modifier = Modifier.height(MaterialTheme.dimens.large150)
                    .padding(vertical = MaterialTheme.dimens.mini120),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text((index + 1).toString())
                Spacer(modifier = Modifier.width(MaterialTheme.dimens.mini120))
                AsyncImage(
                    model = item.imageUrl,
                    modifier = Modifier.clip(CircleShape),
                    contentDescription = "profile photo",
                    error = painterResource(R.drawable.img_profile_placeholder),
                    placeholder = painterResource(R.drawable.img_profile_placeholder),
                )
                Spacer(modifier = Modifier.width(MaterialTheme.dimens.mini120))
                Text(item.username)
                Spacer(Modifier.weight(1f))
                Text("${item.score} Point")
            }
            Divider()
        })
    }
}

@Preview
@Composable
fun Preview() =
    PoluizTheme {
        LeaderboardListOrganism(
            Id.LeaderboardListOrganism,
            LeaderboardOrganismState(
                listOf(
                    Leaderboard(
                        "Budi",
                        "https://picsum.photos/200/300",
                        3
                    )
                )
            )
        )
    }