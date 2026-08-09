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
import androidx.compose.ui.unit.dp
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
            title = "도우미를 찾고 있어요",
            description = "잠시만 기다려 주세요",
            modifier = Modifier.offset(y = 120.dp),
        )
        SeniorLoadingDots(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-10).dp),
        )
        HelloButton(
            text = "취소하기",
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
            title = "대기 중인 도우미가 없어요",
            description = "잠시 후 다시 시도해 주세요",
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
            HelloButton("다시 시도하기", onRetry, style = HelloButtonStyle.Secondary)
            HelloButton("홈으로", onHome)
        }
    }
}
