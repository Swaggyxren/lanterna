package com.swaggyxren.lanterna.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightScheme = lightColorScheme(
    primary               = LanternaLightPrimary,
    onPrimary             = LanternaLightOnPrimary,
    primaryContainer      = LanternaLightPrimaryContainer,
    onPrimaryContainer    = LanternaLightOnPrimaryContainer,
    secondary             = LanternaLightSecondary,
    onSecondary           = LanternaLightOnSecondary,
    secondaryContainer    = LanternaLightSecondaryContainer,
    onSecondaryContainer  = LanternaLightOnSecondaryContainer,
    tertiary              = LanternaLightTertiary,
    onTertiary            = LanternaLightOnTertiary,
    tertiaryContainer     = LanternaLightTertiaryContainer,
    onTertiaryContainer   = LanternaLightOnTertiaryContainer,
    background            = LanternaLightBackground,
    onBackground          = LanternaLightOnBackground,
    surface               = LanternaLightSurface,
    onSurface             = LanternaLightOnSurface,
    surfaceVariant        = LanternaLightSurfaceVariant,
    onSurfaceVariant      = LanternaLightOnSurfaceVariant,
    error                 = LanternaLightError,
    onError               = LanternaLightOnError,
    errorContainer        = LanternaLightErrorContainer,
    onErrorContainer      = LanternaLightOnErrorContainer
)

private val DarkScheme = darkColorScheme(
    primary               = LanternaDarkPrimary,
    onPrimary             = LanternaDarkOnPrimary,
    primaryContainer      = LanternaDarkPrimaryContainer,
    onPrimaryContainer    = LanternaDarkOnPrimaryContainer,
    secondary             = LanternaDarkSecondary,
    onSecondary           = LanternaDarkOnSecondary,
    secondaryContainer    = LanternaDarkSecondaryContainer,
    onSecondaryContainer  = LanternaDarkOnSecondaryContainer,
    tertiary              = LanternaDarkTertiary,
    onTertiary            = LanternaDarkOnTertiary,
    tertiaryContainer     = LanternaDarkTertiaryContainer,
    onTertiaryContainer   = LanternaDarkOnTertiaryContainer,
    background            = LanternaDarkBackground,
    onBackground          = LanternaDarkOnBackground,
    surface               = LanternaDarkSurface,
    onSurface             = LanternaDarkOnSurface,
    surfaceVariant        = LanternaDarkSurfaceVariant,
    onSurfaceVariant      = LanternaDarkOnSurfaceVariant,
    error                 = LanternaDarkError,
    onError               = LanternaDarkOnError,
    errorContainer        = LanternaDarkErrorContainer,
    onErrorContainer      = LanternaDarkOnErrorContainer
)

@Composable
fun LanternaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val ctx = LocalContext.current
    val supportsDynamic = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val scheme = when {
        dynamicColor && supportsDynamic ->
            if (darkTheme) dynamicDarkColorScheme(ctx) else dynamicLightColorScheme(ctx)
        darkTheme -> DarkScheme
        else      -> LightScheme
    }
    MaterialTheme(
        colorScheme = scheme,
        typography  = LanternaTypography,
        content     = content
    )
}
