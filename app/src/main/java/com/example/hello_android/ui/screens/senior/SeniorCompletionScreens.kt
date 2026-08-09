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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
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
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HelloSystemBars(darkStatusIcons = true)
    Box(modifier = modifier.fillMaxSize()) {
        HelloScreenTitle(
            title = "이번 통화 내용 요약이에요",
            description = "이 요약은 한 번만 보여드려요",
            modifier = Modifier.offset(y = 120.dp),
        )
        SeniorSummaryCard(
            summaries = listOf(
                "OO역 3호선 환승 방법에 대해 물어봤어요.",
                "3호선 환승은 2번 출구로 나가 왼쪽으로 이동하세요.",
                "3호선 환승은 2번 출구로 나가 왼쪽으로 이동하세요.",
                "3호선 환승은 2번 출구로 나가 왼쪽으로 이동하세요.",
            ),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 260.dp)
                .width(328.dp),
        )
        HelloButton(
            text = "다음",
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
) {
    HelloSystemBars(darkStatusIcons = true)
    val colors = MaterialTheme.helloColors
    Box(modifier = modifier.fillMaxSize()) {
        HelloScreenTitle(
            title = if (automaticEnd) "통화가 자동 종료되었어요" else "도움이 되었길 바라요",
            description = if (automaticEnd) {
                "AI가 부적절한 행동 · 언행을 감지했어요"
            } else {
                "필요하면 언제든 다시 요청하세요"
            },
            modifier = Modifier.offset(y = 120.dp),
        )
        HelloStatusIcon(
            type = if (automaticEnd) HelloStatusIconType.Alert else HelloStatusIconType.Success,
            modifier = Modifier.align(Alignment.Center),
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
                    text = "불편한 일이 있었나요? 신고하기",
                    style = MaterialTheme.helloTypography.body3,
                    color = colors.textTertiary,
                    textDecoration = TextDecoration.Underline,
                )
            }
            HelloButton(text = "홈으로", onClick = onHome)
        }
    }
}
