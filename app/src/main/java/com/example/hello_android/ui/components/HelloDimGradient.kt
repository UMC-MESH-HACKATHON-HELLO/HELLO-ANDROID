package com.example.hello_android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.hello_android.ui.theme.helloColors

@Composable
fun HelloDimGradient(modifier: Modifier = Modifier) {
    val colors = MaterialTheme.helloColors
    androidx.compose.foundation.layout.Box(
        modifier = modifier
            .fillMaxWidth()
            .height(138.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        colors.surfaceDark,
                        colors.surfaceDark.copy(alpha = 0f),
                    ),
                ),
            ),
    )
}
