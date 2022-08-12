package com.lnco.poluiz.feature.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.lnco.poluiz.core.window.WindowInfo
import com.lnco.poluiz.core.window.WindowType
import com.lnco.poluiz.core.window.rememberWindowInfo

private val LightColorPalette = lightColors(
    primary = carmine_pink,
    primaryVariant = coral_reef,
    secondary = antique_white,
    secondaryVariant = Color.White,
    onPrimary = Color.White,
    onSecondary = davy_grey,
)

@Composable
fun PoluizTheme(
    windowInfo: WindowInfo = rememberWindowInfo(),
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = LightColorPalette

    val dimens = when (windowInfo.windowWidthType) {
        WindowType.COMPACT -> Dimens()
        WindowType.MEDIUM -> Dimens()
        else -> Dimens()
    }

    ProvideDimens(dimens = dimens) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}