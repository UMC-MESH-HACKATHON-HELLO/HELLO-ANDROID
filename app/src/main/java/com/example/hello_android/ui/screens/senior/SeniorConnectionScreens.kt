package com.example.hello_android.ui.screens.senior

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hello_android.R
import com.example.hello_android.ui.components.HelloButton
import com.example.hello_android.ui.components.HelloButtonStyle
import com.example.hello_android.ui.components.HelloScreenTitle
import com.example.hello_android.ui.components.HelloSystemBars
import com.example.hello_android.ui.components.HelloStatusIcon
import com.example.hello_android.ui.components.HelloStatusIconType
import com.example.hello_android.ui.screens.senior.components.SeniorLoadingDots

@Composable
fun SeniorCallingTryingScreen(
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HelloSystemBars(darkStatusIcons = true)
    Box(modifier = modifier.fillMaxSize()) {
        HelloScreenTitle(
            title = stringResource(R.string.senior_call_trying_title),
            description = stringResource(R.string.senior_call_trying_description),
            modifier = Modifier.offset(y = 120.dp),
        )
        SeniorLoadingDots(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-10).dp),
        )
        HelloButton(
            text = stringResource(R.string.senior_call_cancel),
            onClick = onCancel,
            style = HelloButtonStyle.Secondary,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .offset(y = (-20).dp)
                .width(328.dp),
        )
    }
}

@Composable
fun SeniorCallingFailScreen(
    onRetry: () -> Unit,
    onHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HelloSystemBars(darkStatusIcons = true)
    Box(modifier = modifier.fillMaxSize()) {
        HelloScreenTitle(
            title = stringResource(R.string.senior_call_failure_title),
            description = stringResource(R.string.senior_call_failure_description),
            modifier = Modifier.offset(y = 120.dp),
        )
        HelloStatusIcon(
            type = HelloStatusIconType.Error,
            modifier = Modifier.align(Alignment.Center),
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .offset(y = (-20).dp)
                .width(328.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            HelloButton(
                stringResource(R.string.senior_call_retry),
                onRetry,
                style = HelloButtonStyle.Secondary,
            )
            HelloButton(stringResource(R.string.action_home), onHome)
        }
    }
}
