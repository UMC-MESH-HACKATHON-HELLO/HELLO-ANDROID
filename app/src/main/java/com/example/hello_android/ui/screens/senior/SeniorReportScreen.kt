package com.example.hello_android.ui.screens.senior

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hello_android.ui.components.HelloButton
import com.example.hello_android.ui.components.HelloScreenTitle
import com.example.hello_android.ui.components.HelloSystemBars
import com.example.hello_android.ui.components.HelloTopBar
import com.example.hello_android.ui.screens.senior.components.SeniorReportOption

private val seniorReportReasons = listOf(
    "금전 · 계좌를 요구했어요",
    "개인정보를 물어봤어요",
    "무례한 언행을 했어요",
    "불성실하게 응대했어요",
    "기타",
)

@Composable
fun SeniorReportScreen(
    onBack: () -> Unit,
    onSubmit: (Set<String>) -> Unit,
    modifier: Modifier = Modifier,
) {
    val selections = remember {
        mutableStateListOf(true, false, false, false, false)
    }

    HelloSystemBars(darkStatusIcons = true)
    Box(modifier = modifier.fillMaxSize()) {
        HelloTopBar(
            showBack = true,
            onBack = onBack,
            modifier = Modifier.statusBarsPadding(),
        )
        HelloScreenTitle(
            title = "무엇이 문제였나요?",
            description = "신고 사유를 선택 해주세요",
            modifier = Modifier.offset(y = 120.dp),
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 230.dp)
                .width(328.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            seniorReportReasons.forEachIndexed { index, reason ->
                SeniorReportOption(
                    text = reason,
                    selected = selections[index],
                    onClick = { selections[index] = !selections[index] },
                )
            }
        }
        HelloButton(
            text = "신고하기",
            onClick = {
                onSubmit(
                    seniorReportReasons
                        .filterIndexed { index, _ -> selections[index] }
                        .toSet(),
                )
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 20.dp)
                .width(328.dp),
        )
    }
}
