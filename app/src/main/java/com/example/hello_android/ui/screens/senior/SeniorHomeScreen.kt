package com.example.hello_android.ui.screens.senior

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hello_android.ui.components.HelloScreenTitle
import com.example.hello_android.ui.components.HelloSystemBars
import com.example.hello_android.ui.components.HelloTopBar
import com.example.hello_android.ui.screens.senior.components.SeniorCallButton
import com.example.hello_android.ui.theme.helloColors
import com.example.hello_android.ui.theme.helloTypography

@Composable
fun SeniorHomeScreen(
    onStartCall: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HelloSystemBars(darkStatusIcons = true)
    Box(modifier = modifier.fillMaxSize()) {
        HelloTopBar(showLogo = true, modifier = Modifier.statusBarsPadding())
        HelloScreenTitle(
            title = "도움이 필요하신가요?",
            description = "버튼을 눌러 도움을 요청해보세요",
            modifier = Modifier.offset(y = 120.dp),
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 310.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            SeniorCallButton(onClick = onStartCall)
            Text(
                text = "도우미와 통화하기",
                style = MaterialTheme.helloTypography.body2Medium,
                color = MaterialTheme.helloColors.textSecondary,
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 23.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text("응급 상황은 119", style = MaterialTheme.helloTypography.label2, color = MaterialTheme.helloColors.textDisabled)
            Text("범죄 신고는 112", style = MaterialTheme.helloTypography.label2, color = MaterialTheme.helloColors.textDisabled)
        }
    }
}
