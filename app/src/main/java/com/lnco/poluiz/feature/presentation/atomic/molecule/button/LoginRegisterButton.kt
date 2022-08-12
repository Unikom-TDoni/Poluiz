package com.lnco.poluiz.feature.presentation.atomic.molecule.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.lnco.poluiz.feature.presentation.state.element.molecule.button.LoginRegisterButtonState
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun LoginRegisterButton(
    state: LoginRegisterButtonState
) =
    Button(
        onClick = state.onClick,
        modifier = Modifier.fillMaxWidth().height(MaterialTheme.dimens.large150),
    ) {
        Text(
            text = state.text,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }

@Preview
@Composable
private fun Preview() =
    PoluizTheme {
        LoginRegisterButton(
            LoginRegisterButtonState("Login") {}
        )
    }