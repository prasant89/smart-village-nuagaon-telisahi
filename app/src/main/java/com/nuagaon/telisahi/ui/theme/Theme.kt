package com.nuagaon.telisahi.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = VillageGreenPrimary,
    onPrimary = VillageGreenOnPrimary,
    primaryContainer = VillageGreenPrimaryContainer,
    onPrimaryContainer = VillageGreenOnPrimaryContainer,
    secondary = VillageSecondary,
    onSecondary = VillageOnSecondary,
    secondaryContainer = VillageSecondaryContainer,
    onSecondaryContainer = VillageOnSecondaryContainer,
    tertiary = VillageTertiary,
    onTertiary = VillageOnTertiary,
    tertiaryContainer = VillageTertiaryContainer,
    onTertiaryContainer = VillageOnTertiaryContainer,
    error = VillageError,
    onError = VillageOnError,
    background = VillageBackground,
    onBackground = VillageOnBackground,
    surface = VillageSurface,
    onSurface = VillageOnSurface,
    surfaceVariant = VillageOnSurfaceVariant,
    onSurfaceVariant = VillageOnSurfaceVariant
)

private val LightColorScheme = lightColorScheme(
    primary = VillageGreenPrimary,
    onPrimary = VillageGreenOnPrimary,
    primaryContainer = VillageGreenPrimaryContainer,
    onPrimaryContainer = VillageGreenOnPrimaryContainer,
    secondary = VillageSecondary,
    onSecondary = VillageOnSecondary,
    secondaryContainer = VillageSecondaryContainer,
    onSecondaryContainer = VillageOnSecondaryContainer,
    tertiary = VillageTertiary,
    onTertiary = VillageOnTertiary,
    tertiaryContainer = VillageTertiaryContainer,
    onTertiaryContainer = VillageOnTertiaryContainer,
    error = VillageError,
    onError = VillageOnError,
    background = VillageBackground,
    onBackground = VillageOnBackground,
    surface = VillageSurface,
    onSurface = VillageOnSurface,
    surfaceVariant = VillageSurfaceVariant,
    onSurfaceVariant = VillageOnSurfaceVariant,
    outline = VillageOutline
)

@Composable
fun NuagaonTelisahiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Set to false to prioritize our premium village theme
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}