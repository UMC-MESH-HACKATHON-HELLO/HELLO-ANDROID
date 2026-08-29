package com.example.hello_android.ui.screens.senior.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.hello_android.ui.theme.helloColors

@Composable
fun SeniorLoadingDots(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "helper search")
    val colors = MaterialTheme.helloColors
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        repeat(3) { index ->
            val offset = transition.animateFloat(
                initialValue = 4f,
                targetValue = -4f,
                animationSpec = infiniteRepeatable(
                    animation = tween(480),
                    repeatMode = RepeatMode.Reverse,
                    initialStartOffset = StartOffset(index * 120, StartOffsetType.Delay),
                ),
                label = "dot $index",
            )
            androidx.compose.foundation.layout.Box(
                modifier = Modifier
                    .offset { IntOffset(0, offset.value.dp.roundToPx()) }
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(colors.actionPrimary.copy(alpha = 0.6f + index * 0.2f)),
            )
        }
    }
}
