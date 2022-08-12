package com.lnco.poluiz.feature.presentation.atomic.molecule.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.lnco.poluiz.feature.presentation.state.element.molecule.button.MenuButtonState
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun MenuButton(
    id: String = String(),
    state: MenuButtonState
) =
    Button(
        onClick = state.onClick,
        modifier = Modifier.layoutId(id)
            .height(MaterialTheme.dimens.large150)
            .fillMaxWidth(),
    ) {
        Text(
            text = state.text,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }

@Preview
@Composable
private fun Preview() {
    PoluizTheme {
        MenuButton(
            state = MenuButtonState(
                "Main Menu"
            ) {}
        )
    }
}
