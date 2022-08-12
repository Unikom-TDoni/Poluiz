package com.lnco.poluiz.feature.presentation.atomic.molecule

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lnco.poluiz.R
import com.lnco.poluiz.feature.presentation.state.element.molecule.PlayerHealthState
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.antique_white
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun RowScope.PlayerHealth(state: PlayerHealthState) {
    Icon(
        painterResource(R.drawable.ic_hearth),
        contentDescription = "",
        tint = antique_white,
        modifier = Modifier.height(24.dp)
    )
    Spacer(modifier = Modifier.width(MaterialTheme.dimens.mini120))
    Text(
        text = state.life,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(end = MaterialTheme.dimens.mini80)
    )
}


@Preview
@Composable
private fun Preview() =
    PoluizTheme {
        Row {
            PlayerHealth(PlayerHealthState(3))
        }
    }