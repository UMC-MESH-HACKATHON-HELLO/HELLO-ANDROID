package com.example.hello_android.ui.screens.senior.components

import androidx.compose.foundation.border
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hello_android.R
import com.example.hello_android.ui.theme.helloColors
import com.example.hello_android.ui.theme.helloTypography

@Composable
fun SeniorSummaryCard(
    summaries: List<String>,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.helloColors
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(330.dp)
            .border(1.dp, colors.borderSubtle, RoundedCornerShape(10.dp))
            .padding(horizontal = 22.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Image(
                painter = painterResource(R.drawable.ic_auto_awesome),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
            )
            Text(
                text = "AI 요약",
                style = MaterialTheme.helloTypography.body2Strong,
                color = colors.textPrimary,
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            summaries.forEach { summary ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.Top,
                ) {
                    Text(
                        text = "•",
                        style = MaterialTheme.helloTypography.body3,
                        color = colors.textPrimary,
                    )
                    Text(
                        text = summary,
                        style = MaterialTheme.helloTypography.body3,
                        color = colors.textPrimary,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}
