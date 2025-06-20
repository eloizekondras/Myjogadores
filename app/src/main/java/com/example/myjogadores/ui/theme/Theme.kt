package com.example.myjogadores.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(
    primary = HighlightBlue,
    onPrimary = TextPrimary,
    background = BackgroundDark,
    surface = CardBackground,
    onSurface = TextPrimary,
    secondary = HighlightBlue,
    onSecondary = TextPrimary
)

@Composable
fun MyJogadoresTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColors,
        typography = MyTypography,
        shapes = MyShapes,
        content = content
    )
}
