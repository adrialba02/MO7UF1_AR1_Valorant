package com.example.companionapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = ValorantRed,
    onPrimary = ValorantWhite,
    secondary = ValorantCyan,
    onSecondary = ValorantBlack,
    background = BackgroundDark,
    onBackground = ValorantWhite,
    surface = ValorantGray,
    onSurface = ValorantWhite
)

private val LightColorScheme = lightColorScheme(
    primary = ValorantRed,
    onPrimary = ValorantBlack,
    secondary = ValorantCyan,
    onSecondary = ValorantWhite,
    background = ValorantWhite,
    onBackground = ValorantBlack,
    surface = ValorantGray,
    onSurface = ValorantBlack
)

@Composable
fun CompanionAppTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
