package com.lnco.poluiz.core.window

import androidx.compose.ui.unit.Dp

data class WindowInfo(
    val windowWidthType: WindowType,
    val windowHeightType: WindowType,
    val windowWidthDp: Dp,
    val windowHeightDp: Dp
)
