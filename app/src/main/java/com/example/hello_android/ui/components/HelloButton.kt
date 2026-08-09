package com.example.hello_android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hello_android.ui.theme.helloColors
import com.example.hello_android.ui.theme.helloTypography

enum class HelloButtonStyle {
    Primary,
    Secondary,
    Disabled,
}

@Composable
fun HelloButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: HelloButtonStyle = HelloButtonStyle.Primary,
    enabled: Boolean = style != HelloButtonStyle.Disabled,
    leadingContent: (@Composable () -> Unit)? = null,
) {
    val colors = MaterialTheme.helloColors
    val resolvedStyle = if (enabled) style else HelloButtonStyle.Disabled
    val background: Color
    val content: Color
    val border: Color?

    when (resolvedStyle) {
        HelloButtonStyle.Primary -> {
            background = colors.actionPrimary
            content = colors.textOnColor
            border = null
        }
        HelloButtonStyle.Secondary -> {
            background = colors.surfaceDefault
            content = colors.textSecondary
            border = colors.borderDisabled
        }
        HelloButtonStyle.Disabled -> {
            background = colors.surfaceDisabled
            content = colors.textDisabled
            border = null
        }
    }

    val shape = RoundedCornerShape(10.dp)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(shape)
            .background(background)
            .then(if (border != null) Modifier.border(1.dp, border, shape) else Modifier)
            .then(if (enabled) Modifier.clickable(onClick = onClick) else Modifier),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingContent?.invoke()
        Text(
            text = text,
            style = MaterialTheme.helloTypography.label1Medium,
            color = content,
        )
    }
}
