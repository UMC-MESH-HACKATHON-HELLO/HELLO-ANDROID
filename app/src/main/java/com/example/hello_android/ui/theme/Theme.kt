package com.example.hello_android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val LightColorScheme = lightColorScheme(
    primary = HelloPrimitiveColors.Orange,
    onPrimary = HelloPrimitiveColors.White,
    secondary = HelloPrimitiveColors.Activated,
    onSecondary = HelloPrimitiveColors.White,
    background = HelloPrimitiveColors.White,
    onBackground = HelloPrimitiveColors.TextBlack,
    surface = HelloPrimitiveColors.White,
    onSurface = HelloPrimitiveColors.TextBlack,
    surfaceVariant = HelloPrimitiveColors.DisabledFill,
    onSurfaceVariant = HelloPrimitiveColors.TextSub1,
    error = HelloPrimitiveColors.Error,
    onError = HelloPrimitiveColors.White,
    errorContainer = HelloPrimitiveColors.ErrorFill,
    onErrorContainer = HelloPrimitiveColors.Error,
    outline = HelloPrimitiveColors.Border,
    outlineVariant = HelloPrimitiveColors.IconDefault,
)

@Composable
fun Hello_AndroidTheme(
    content: @Composable () -> Unit
) {
    // Figma currently defines one light palette. Dynamic colors are intentionally
    // disabled so Android does not replace the approved design tokens.
    CompositionLocalProvider(
        LocalHelloColors provides HelloLightColors,
        LocalHelloTypography provides HelloTextStyles,
    ) {
        MaterialTheme(
            colorScheme = LightColorScheme,
            typography = HelloMaterialTypography,
            content = content,
        )
    }
}
