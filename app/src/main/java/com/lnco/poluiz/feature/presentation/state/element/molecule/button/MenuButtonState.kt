package com.lnco.poluiz.feature.presentation.state.element.molecule.button

import javax.annotation.concurrent.Immutable

@Immutable
class MenuButtonState(
    text: String,
    onClick: () -> Unit
) : ButtonState(text) {
    override val onClick: () -> Unit = {
        onClick()
    }
}