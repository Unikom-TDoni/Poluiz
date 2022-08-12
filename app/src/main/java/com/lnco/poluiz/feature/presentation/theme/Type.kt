package com.lnco.poluiz.feature.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.lnco.poluiz.R

@OptIn(ExperimentalTextApi::class)
private val googleFontProvider: GoogleFont.Provider by lazy {
    GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
}

@OptIn(ExperimentalTextApi::class)
private fun getGoogleFontFamily(
    name: String,
    provider: GoogleFont.Provider = googleFontProvider,
    weights: List<FontWeight>
) = FontFamily(
    weights.map {
        Font(GoogleFont(name), provider, it)
    }
)


@OptIn(ExperimentalTextApi::class)
val PoppinsFontFamily = getGoogleFontFamily(
    name = "Poppins",
    weights = listOf(
        FontWeight.Normal,
        FontWeight.Light,
        FontWeight.Medium
    )
)

@OptIn(ExperimentalTextApi::class)
val OpenSansFontFamily = getGoogleFontFamily(
    name = "Open Sans",
    weights = listOf(
        FontWeight.Normal,
        FontWeight.Medium,
        FontWeight.SemiBold
    )
)

@OptIn(ExperimentalTextApi::class)
val OleoScriptSwashCapsFontFamily = getGoogleFontFamily(
    name = "Oleo Script Swash Caps",
    weights = listOf(
        FontWeight.Bold
    )
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 93.sp,
        letterSpacing = (-1.5).sp,
    ),
    h2 = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 58.sp,
        letterSpacing = (-0.5).sp,
    ),
    h3 = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 46.sp,
        letterSpacing = 0.sp,
    ),
    h4 = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 33.sp,
        letterSpacing = (0.25).sp,
    ),
    h5 = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 23.sp,
        letterSpacing = 0.sp,
    ),
    h6 = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 19.sp,
        letterSpacing = (0.15).sp,
    ),
    subtitle1 = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        letterSpacing = (0.15).sp,
    ),
    subtitle2 = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        letterSpacing = (0.1).sp,
    ),
    body1 = TextStyle(
        fontFamily = OpenSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = (0.5).sp,
    ),
    body2 = TextStyle(
        fontFamily = OpenSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = (0.25).sp,
    ),
    button = TextStyle(
        fontFamily = OpenSansFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        letterSpacing = (1.25).sp,
    ),
    caption = TextStyle(
        fontFamily = OpenSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = (0.4).sp,
    ),
    overline = TextStyle(
        fontFamily = OpenSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = (1.5).sp,
    )
)