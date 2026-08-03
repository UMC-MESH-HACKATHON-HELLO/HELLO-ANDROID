package com.example.hello_android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.hello_android.R

val PretendardFontFamily = FontFamily(
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_bold, FontWeight.Bold),
)

private fun figmaTextStyle(
    size: TextUnit,
    lineHeight: TextUnit,
    weight: FontWeight,
): TextStyle = TextStyle(
    fontFamily = PretendardFontFamily,
    fontWeight = weight,
    fontSize = size,
    lineHeight = lineHeight,
    // The current Figma font system specifies -2 px for every text style.
    letterSpacing = (-2).sp,
    platformStyle = PlatformTextStyle(includeFontPadding = false),
)

@Immutable
data class HelloTypography(
    val title1: TextStyle,
    val title2: TextStyle,
    val title3: TextStyle,
    val title3Strong: TextStyle,
    val title4: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val body2Medium: TextStyle,
    val body2Strong: TextStyle,
    val body3: TextStyle,
    val body3Medium: TextStyle,
    val field1: TextStyle,
    val label1: TextStyle,
    val label1Medium: TextStyle,
    val label2: TextStyle,
)

internal val HelloTextStyles = HelloTypography(
    title1 = figmaTextStyle(32.sp, 48.sp, FontWeight.SemiBold),
    title2 = figmaTextStyle(28.sp, 42.sp, FontWeight.SemiBold),
    title3 = figmaTextStyle(24.sp, 36.sp, FontWeight.Normal),
    title3Strong = figmaTextStyle(24.sp, 36.sp, FontWeight.SemiBold),
    title4 = figmaTextStyle(22.sp, 33.sp, FontWeight.Normal),
    body1 = figmaTextStyle(22.sp, 33.sp, FontWeight.Normal),
    body2 = figmaTextStyle(20.sp, 30.sp, FontWeight.Normal),
    body2Medium = figmaTextStyle(20.sp, 30.sp, FontWeight.Medium),
    body2Strong = figmaTextStyle(20.sp, 30.sp, FontWeight.SemiBold),
    body3 = figmaTextStyle(16.sp, 24.sp, FontWeight.Normal),
    body3Medium = figmaTextStyle(16.sp, 24.sp, FontWeight.Medium),
    field1 = figmaTextStyle(20.sp, 30.sp, FontWeight.Normal),
    label1 = figmaTextStyle(16.sp, 21.sp, FontWeight.Normal),
    label1Medium = figmaTextStyle(16.sp, 21.sp, FontWeight.Medium),
    label2 = figmaTextStyle(14.sp, 18.sp, FontWeight.Normal),
)

internal val HelloMaterialTypography = Typography(
    displayLarge = HelloTextStyles.title1,
    displayMedium = HelloTextStyles.title2,
    displaySmall = HelloTextStyles.title3Strong,
    headlineLarge = HelloTextStyles.title2,
    headlineMedium = HelloTextStyles.title3Strong,
    headlineSmall = HelloTextStyles.title3,
    titleLarge = HelloTextStyles.title4,
    titleMedium = HelloTextStyles.body2Medium,
    titleSmall = HelloTextStyles.body3Medium,
    bodyLarge = HelloTextStyles.body1,
    bodyMedium = HelloTextStyles.body2,
    bodySmall = HelloTextStyles.body3,
    labelLarge = HelloTextStyles.label1Medium,
    labelMedium = HelloTextStyles.label1,
    labelSmall = HelloTextStyles.label2,
)

internal val LocalHelloTypography = staticCompositionLocalOf { HelloTextStyles }

val MaterialTheme.helloTypography: HelloTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalHelloTypography.current
