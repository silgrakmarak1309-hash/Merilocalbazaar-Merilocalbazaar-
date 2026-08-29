package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = BazaarOrangeLight,
    onPrimary = Color.White,
    primaryContainer = BazaarOrangeDark,
    onPrimaryContainer = Color.White,
    secondary = SaffronAmber,
    onSecondary = NavyDark,
    tertiary = EmeraldSuccess,
    background = NavyDark,
    surface = SlateCard,
    surfaceVariant = Color(0xFF334155),
    onBackground = Color.White,
    onSurface = Color.White,
    onSurfaceVariant = Color(0xFFCBD5E1),
    outline = Color(0xFF475569)
)

private val LightColorScheme = lightColorScheme(
    primary = BazaarOrange,
    onPrimary = Color.White,
    primaryContainer = SaffronLight,
    onPrimaryContainer = BazaarOrangeDark,
    secondary = NavyDark,
    onSecondary = Color.White,
    tertiary = EmeraldSuccess,
    background = LightBackground,
    surface = Color.White,
    surfaceVariant = LightSurface,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onSurfaceVariant = TextSecondary,
    outline = SlateBorder
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Keep branded colors for high-trust consistent identity
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
