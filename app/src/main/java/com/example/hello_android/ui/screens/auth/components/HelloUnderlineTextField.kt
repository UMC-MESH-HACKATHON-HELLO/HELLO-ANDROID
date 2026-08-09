package com.example.hello_android.ui.screens.auth.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.hello_android.ui.theme.helloColors
import com.example.hello_android.ui.theme.helloTypography

@Composable
fun HelloUnderlineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    supportingMessage: String? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    onFocusChange: ((Boolean) -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    val colors = MaterialTheme.helloColors
    val interactionSource = remember { MutableInteractionSource() }
    val focused by interactionSource.collectIsFocusedAsState()
    val lineColor = when {
        errorMessage != null -> colors.statusError
        focused -> colors.textPrimary
        value.isNotEmpty() -> colors.borderDisabled
        else -> colors.textTertiary
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.helloTypography.label1,
            color = colors.textSecondary,
            modifier = Modifier.padding(start = 4.dp),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .weight(1f)
                    .semantics { contentDescription = label }
                    .onFocusChanged { onFocusChange?.invoke(it.isFocused) },
                textStyle = MaterialTheme.helloTypography.field1.copy(color = colors.textPrimary),
                singleLine = true,
                interactionSource = interactionSource,
                cursorBrush = SolidColor(colors.textPrimary),
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                decorationBox = { innerTextField ->
                    Box(contentAlignment = Alignment.CenterStart) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                style = MaterialTheme.helloTypography.field1,
                                color = colors.textDisabled,
                            )
                        }
                        innerTextField()
                    }
                },
            )
            trailingContent?.invoke()
        }
        androidx.compose.foundation.Canvas(Modifier.fillMaxWidth().height(1.dp)) {
            drawRect(lineColor)
        }
        val message = errorMessage ?: supportingMessage
        if (message != null) {
            Text(
                text = message,
                style = MaterialTheme.helloTypography.label2,
                color = if (errorMessage != null) colors.statusError else colors.textTertiary,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp),
            )
        }
    }
}
