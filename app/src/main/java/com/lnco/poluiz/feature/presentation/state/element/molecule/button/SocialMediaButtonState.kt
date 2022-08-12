package com.lnco.poluiz.feature.presentation.state.element.molecule.button

import javax.annotation.concurrent.Immutable

@Immutable
class SocialMediaButtonState(
    text: String,
    val icon: Int,
    onClick: () -> Unit
) : ButtonState(text) {
    override val onClick: () -> Unit = {
        onClick()
    }
}