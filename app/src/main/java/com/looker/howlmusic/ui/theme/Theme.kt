package com.looker.howlmusic.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = OrangeDark,
    primaryVariant = BlackGrey,
    secondary = SkyDark,
    background = BlackGrey,
    surface = GreyOnBlack
)

private val LightColorPalette = lightColors(
    primary = Orange,
    primaryVariant = Color.White,
    secondary = Sky,
    background = Color.White,
    surface = WhiteOnWhite
)

@Composable
fun HowlMusicTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}