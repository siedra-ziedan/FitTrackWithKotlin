package com.example.greeting.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryOrange,
    secondary = SecondaryBlue,
    tertiary = TertiaryBlue,
    background = DarkBackground,
    surface = DarkSurface,
    onBackground = TextWhite,
    onSurface = TextWhite
)

@Composable
fun GreetingTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}