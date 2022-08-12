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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.lnco.poluiz.feature.presentation.state.element.molecule.button.CheckButtonState
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun CheckButton(
    state: CheckButtonState
) =
    Button(
        onClick = state.onClick,
        modifier = Modifier.height(MaterialTheme.dimens.extraLarge106)
            .fillMaxWidth()
            .padding(
                end = MaterialTheme.dimens.normal100,
                start = MaterialTheme.dimens.normal100,
                bottom = MaterialTheme.dimens.normal100
            ),
        colors = ButtonDefaults.buttonColors(backgroundColor = state.color),
        enabled = state.enabled,
    ) {
        Text(
            color = Color.White,
            text = state.text,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }

@Preview
@Composable
private fun Preview() =
    PoluizTheme {
        CheckButton(CheckButtonState("Check") {})
    }