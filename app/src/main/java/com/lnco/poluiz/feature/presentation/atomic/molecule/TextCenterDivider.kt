package com.lnco.poluiz.feature.presentation.atomic.molecule

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import com.lnco.poluiz.feature.presentation.theme.Id
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun TextCenterDivider(
    id: String,
    text: String,
) {
    Row(
        modifier = Modifier.layoutId(id),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            thickness = MaterialTheme.dimens.mini20,
            modifier = Modifier.weight(0.5f).padding(end = MaterialTheme.dimens.mini120),
        )
        Text(text = text)
        Divider(
            thickness = MaterialTheme.dimens.mini20,
            modifier = Modifier.weight(0.5f).padding(start = MaterialTheme.dimens.mini120)
        )
    }
}

@Preview
@Composable
private fun Preview() =
    PoluizTheme {
        TextCenterDivider(Id.TextCenterDivider, "OR")
    }