package com.lnco.poluiz.feature.presentation.atomic.molecule.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.lnco.poluiz.feature.presentation.state.element.molecule.button.ChoiceButtonState
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun ChoiceButton(
    state: ChoiceButtonState
) =
    Button(
        onClick = state.onClick,
        modifier = Modifier.height(MaterialTheme.dimens.large162)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = state.color)
    ) {
        Text(
            text = state.text,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(MaterialTheme.dimens.mini80),
        )
    }

@Preview
@Composable
private fun Preview() =
    PoluizTheme {
        ChoiceButton(
            ChoiceButtonState("When") {}
        )
    }
