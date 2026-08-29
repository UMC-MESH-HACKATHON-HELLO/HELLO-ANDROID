package com.example.hello_android.ui.screens.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hello_android.ui.theme.helloColors
import com.example.hello_android.ui.theme.helloTypography

@Composable
fun NumberedInstruction(
    number: Int,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(MaterialTheme.helloColors.surfaceBrandSubtle, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = number.toString(),
                style = MaterialTheme.helloTypography.body3Medium,
                color = MaterialTheme.helloColors.textPrimary,
            )
        }
        Text(
            text = text,
            style = MaterialTheme.helloTypography.body3Medium,
            color = MaterialTheme.helloColors.textPrimary,
        )
    }
}
