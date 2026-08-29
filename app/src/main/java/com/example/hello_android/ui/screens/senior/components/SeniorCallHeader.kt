package com.example.hello_android.ui.screens.senior.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hello_android.ui.theme.helloColors
import com.example.hello_android.ui.theme.helloTypography

@Composable
fun SeniorCallHeader(
    mode: String,
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    elapsed: String = "00:03",
) {
    val colors = MaterialTheme.helloColors
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(mode, style = MaterialTheme.helloTypography.body3Medium, color = colors.textOnColor)
            Text(elapsed, style = MaterialTheme.helloTypography.body3, color = colors.textOnColor)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.helloTypography.title2,
                color = colors.textOnColor,
                textAlign = TextAlign.Center,
            )
            if (description != null) {
                Text(
                    text = description,
                    style = MaterialTheme.helloTypography.label1,
                    color = colors.textOnColor,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
