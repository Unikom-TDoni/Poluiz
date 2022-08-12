package com.lnco.poluiz.feature.presentation.atomic.organism

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.layoutId
import com.google.common.collect.ImmutableCollection
import com.google.common.collect.ImmutableList
import com.lnco.poluiz.R
import com.lnco.poluiz.core.window.rememberWindowInfo
import com.lnco.poluiz.feature.domain.ui.SocialMediaNames
import com.lnco.poluiz.feature.presentation.atomic.molecule.button.SocialMediaButtoOrganism
import com.lnco.poluiz.feature.presentation.state.element.molecule.button.SocialMediaButtonState
import com.lnco.poluiz.feature.presentation.theme.Id
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun SocialMediaButtoOrganism(
    id: String,
    states: ImmutableCollection<SocialMediaButtonState>,
) = Column(
    modifier = Modifier.layoutId(id)
) {
    for (item in states) {
        SocialMediaButtoOrganism(item)
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.normal100))
    }
}

@Preview
@Composable
private fun Preview() =
    PoluizTheme(windowInfo = rememberWindowInfo()) {
        SocialMediaButtoOrganism(
            Id.SocialMediaButtonOrganism,
            ImmutableList.of(
                SocialMediaButtonState(
                    SocialMediaNames.Google.toString(),
                    R.drawable.ic_google
                ) {},
                SocialMediaButtonState(
                    SocialMediaNames.Facebook.toString(),
                    R.drawable.ic_facebook
                ) {})
        )
    }