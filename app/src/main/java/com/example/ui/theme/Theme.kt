package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme =
  darkColorScheme(
    primary = BazaarOrangeLight,
    onPrimary = Slate900,
    primaryContainer = BazaarOrangeDark,
    onPrimaryContainer = Color.White,
    secondary = BazaarTeal,
    onSecondary = Color.White,
    secondaryContainer = Slate800,
    onSecondaryContainer = Slate100,
    tertiary = BazaarGold,
    background = Color(0xFF090D16),
    surface = Slate900,
    onBackground = Color(0xFFF1F5F9),
    onSurface = Color(0xFFF1F5F9),
    surfaceVariant = Slate800,
    onSurfaceVariant = Slate400,
    outline = Slate700
  )

private val LightColorScheme =
  lightColorScheme(
    primary = BazaarOrange,
    onPrimary = Color.White,
    primaryContainer = BazaarOrangeContainer,
    onPrimaryContainer = OnBazaarOrangeContainer,
    secondary = BazaarTeal,
    onSecondary = Color.White,
    secondaryContainer = BazaarTealContainer,
    onSecondaryContainer = BazaarTealDark,
    tertiary = BazaarGold,
    background = Slate50,
    surface = Color.White,
    onBackground = Slate900,
    onSurface = Slate900,
    surfaceVariant = Slate100,
    onSurfaceVariant = Slate600,
    outline = Slate200
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
