package com.lnco.poluiz.feature.presentation.state.element.molecule

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Stable
class PlayerHealthState(
    life: Int
) {
    var life by mutableStateOf(life.toString())
        private set

    fun updateLife(life: Int) {
        this.life = life.toString()
    }
}