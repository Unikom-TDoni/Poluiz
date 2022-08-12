package com.lnco.poluiz.feature.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val mini20: Dp = 2.dp,
    val mini40: Dp = 4.dp,
    val mini80: Dp = 8.dp,
    val mini120: Dp = 12.dp,
    val normal100: Dp = 16.dp,
    val normal125: Dp = 20.dp,
    val normal150: Dp = 24.dp,
    val normal175: Dp = 28.dp,
    val large100: Dp = 32.dp,
    val large115: Dp = 36.dp,
    val large125: Dp = 40.dp,
    val large137: Dp = 44.dp,
    val large150: Dp = 48.dp,
    val large162: Dp = 52.dp,
    val large175: Dp = 56.dp,
    val large187: Dp = 60.dp,
    val extraLarge100: Dp = 64.dp,
    val extraLarge106: Dp = 68.dp,
    val extraLarge112: Dp = 72.dp,
    val extraLarge118: Dp = 76.dp,
    val extraLarge125: Dp = 80.dp,
    val extraLarge137: Dp = 88.dp,
    val congratulationImage: Dp = 120.dp
)

private val LocalDimens = staticCompositionLocalOf {
    Dimens()
}

@Composable
fun ProvideDimens(
    dimens: Dimens,
    content: @Composable () -> Unit
) {
    val dimensionSet = remember { dimens }
    CompositionLocalProvider(LocalDimens provides dimensionSet, content = content)
}

val MaterialTheme.dimens: Dimens
    @Composable
    @ReadOnlyComposable
    get() = LocalDimens.current
