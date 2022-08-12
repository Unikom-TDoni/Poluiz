package com.lnco.poluiz.feature.presentation.atomic.molecule.button

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lnco.poluiz.R
import com.lnco.poluiz.feature.domain.ui.SocialMediaNames
import com.lnco.poluiz.feature.presentation.state.element.molecule.button.SocialMediaButtonState
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun SocialMediaButtoOrganism(
    state: SocialMediaButtonState,
) =
    Button(
        onClick = state.onClick,
        modifier = Modifier.fillMaxWidth().height(MaterialTheme.dimens.large150),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
    ) {
        Icon(
            painter = painterResource(state.icon),
            contentDescription = "icon",
            tint = Color.Unspecified,
        )
        Spacer(modifier = Modifier.width(MaterialTheme.dimens.normal100))
        Text(
            text = "Continue With ${state.text}",
        )
    }

@Preview
@Composable
private fun Preview() =
    PoluizTheme {
        SocialMediaButtoOrganism(
            SocialMediaButtonState(
                SocialMediaNames.Google.toString(),
                R.drawable.ic_google
            ) {})
    }