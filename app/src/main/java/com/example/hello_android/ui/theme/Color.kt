package com.example.hello_android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/** Raw values transcribed from the Figma color system. */
internal object HelloPrimitiveColors {
    val White = Color(0xFFFFFFFF)
    val TextBlack = Color(0xFF111111)
    val TextSub1 = Color(0xFF505050)
    val TextSub2 = Color(0xFF767676)
    val TextDisabled = Color(0xFF999999)
    val IconDefault = Color(0xFFACACAC)
    val Border = Color(0xFFE0E0E0)
    val BorderSubtle = Color(0xFFEAEAEA)
    val BorderSelection = Color(0xFFEDEAEA)
    val DisabledFill = Color(0xFFF5F5F5)

    val Orange = Color(0xFFF16022)
    val OrangeBackground = Color(0xFFFF773D)
    val OrangeSubtle = Color(0xFFFEEFE9)
    val Activated = Color(0xFF4A4AFA)
    val ActivatedFill = Color(0xFFF2F6FF)
    val Error = Color(0xFFEF2B2A)
    val ErrorFill = Color(0xFFFFE4E3)
    val On = Color(0xFF2FD656)
    val Kakao = Color(0xFFFEE500)
    val CameraFallback = Color(0xFF101010)
}

/** Semantic colors used by screens and components. Raw hex values stay private. */
@Immutable
data class HelloColors(
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val textDisabled: Color,
    val textOnColor: Color,
    val actionPrimary: Color,
    val actionActivated: Color,
    val iconPrimary: Color,
    val iconDefault: Color,
    val iconOnColor: Color,
    val statusError: Color,
    val statusOn: Color,
    val surfaceDefault: Color,
    val surfaceDisabled: Color,
    val surfaceActivated: Color,
    val surfaceError: Color,
    val surfaceBrand: Color,
    val surfaceBrandSubtle: Color,
    val surfaceDark: Color,
    val surfaceCameraFallback: Color,
    val surfaceKakao: Color,
    val borderDefault: Color,
    val borderDisabled: Color,
    val borderSubtle: Color,
    val borderSelection: Color,
)

internal val HelloLightColors = HelloColors(
    textPrimary = HelloPrimitiveColors.TextBlack,
    textSecondary = HelloPrimitiveColors.TextSub1,
    textTertiary = HelloPrimitiveColors.TextSub2,
    textDisabled = HelloPrimitiveColors.TextDisabled,
    textOnColor = HelloPrimitiveColors.White,
    actionPrimary = HelloPrimitiveColors.Orange,
    actionActivated = HelloPrimitiveColors.Activated,
    iconPrimary = HelloPrimitiveColors.TextBlack,
    iconDefault = HelloPrimitiveColors.IconDefault,
    iconOnColor = HelloPrimitiveColors.White,
    statusError = HelloPrimitiveColors.Error,
    statusOn = HelloPrimitiveColors.On,
    surfaceDefault = HelloPrimitiveColors.White,
    surfaceDisabled = HelloPrimitiveColors.DisabledFill,
    surfaceActivated = HelloPrimitiveColors.ActivatedFill,
    surfaceError = HelloPrimitiveColors.ErrorFill,
    surfaceBrand = HelloPrimitiveColors.OrangeBackground,
    surfaceBrandSubtle = HelloPrimitiveColors.OrangeSubtle,
    surfaceDark = HelloPrimitiveColors.TextBlack,
    surfaceCameraFallback = HelloPrimitiveColors.CameraFallback,
    surfaceKakao = HelloPrimitiveColors.Kakao,
    borderDefault = HelloPrimitiveColors.Border,
    borderDisabled = HelloPrimitiveColors.IconDefault,
    borderSubtle = HelloPrimitiveColors.BorderSubtle,
    borderSelection = HelloPrimitiveColors.BorderSelection,
)

internal val LocalHelloColors = staticCompositionLocalOf { HelloLightColors }

val MaterialTheme.helloColors: HelloColors
    @Composable
    @ReadOnlyComposable
    get() = LocalHelloColors.current
