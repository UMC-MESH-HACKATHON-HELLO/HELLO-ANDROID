package com.example.hello_android.ui.screens.senior

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.hello_android.R
import com.example.hello_android.ui.components.HelloButton
import com.example.hello_android.ui.components.HelloScreenTitle
import com.example.hello_android.ui.components.HelloSystemBars
import com.example.hello_android.ui.components.HelloStatusIcon
import com.example.hello_android.ui.components.HelloStatusIconType
import com.example.hello_android.ui.screens.senior.components.SeniorSummaryCard
import com.example.hello_android.ui.theme.helloColors
import com.example.hello_android.ui.theme.helloTypography

@Composable
fun SeniorCallingSummaryScreen(
    summaries: List<String>,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HelloSystemBars(darkStatusIcons = true)
    Box(modifier = modifier.fillMaxSize()) {
        HelloScreenTitle(
            title = stringResource(R.string.senior_summary_title),
            description = stringResource(R.string.senior_summary_description),
            modifier = Modifier.offset(y = 120.dp),
        )
        SeniorSummaryCard(
            summaries = summaries,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 260.dp)
                .width(328.dp),
        )
        HelloButton(
            text = stringResource(R.string.action_next),
            onClick = onNext,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .offset(y = (-20).dp)
                .width(328.dp),
        )
    }
}

@Composable
fun SeniorCallEndScreen(
    onReport: () -> Unit,
    onHome: () -> Unit,
    modifier: Modifier = Modifier,
    automaticEnd: Boolean = false,
    categoryAndTime: String? = null,
) {
    HelloSystemBars(darkStatusIcons = true)
    val colors = MaterialTheme.helloColors
    Box(modifier = modifier.fillMaxSize()) {
        HelloScreenTitle(
            title = stringResource(
                if (automaticEnd) R.string.senior_call_automatic_end_title
                else R.string.senior_call_end_title,
            ),
            description = if (automaticEnd) {
                stringResource(R.string.senior_call_automatic_end_description)
            } else {
                stringResource(R.string.senior_call_end_description)
            },
            modifier = Modifier.offset(y = 120.dp),
        )
        HelloStatusIcon(
            type = if (automaticEnd) HelloStatusIconType.Alert else HelloStatusIconType.Success,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 320.dp),
        )
        Text(
            text = categoryAndTime ?: stringResource(R.string.senior_call_end_category_time),
            style = MaterialTheme.helloTypography.body3,
            color = colors.textDisabled,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 460.dp),
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .offset(y = (-20).dp)
                .width(328.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Box(
                modifier = Modifier
                    .width(215.dp)
                    .padding(vertical = 13.dp)
                    .clickable(onClick = onReport),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(R.string.senior_report_prompt),
                    style = MaterialTheme.helloTypography.body3,
                    color = colors.textTertiary,
                    textDecoration = TextDecoration.Underline,
                )
            }
            HelloButton(text = stringResource(R.string.action_home), onClick = onHome)
        }
    }
}
