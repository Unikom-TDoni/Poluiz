package com.lnco.poluiz.core.window

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun rememberWindowInfo(): WindowInfo {
    val configuration = LocalConfiguration.current
    return WindowInfo(
        windowWidthType = when {
            configuration.screenWidthDp < 600 -> WindowType.COMPACT
            configuration.screenWidthDp < 840 -> WindowType.MEDIUM
            else -> WindowType.EXPANDED
        },
        windowHeightType = when {
            configuration.screenHeightDp < 480 -> WindowType.COMPACT
            configuration.screenHeightDp < 900 -> WindowType.MEDIUM
            else -> WindowType.EXPANDED
        },
        windowWidthDp = configuration.screenWidthDp.dp,
        windowHeightDp = configuration.screenHeightDp.dp
    )
}