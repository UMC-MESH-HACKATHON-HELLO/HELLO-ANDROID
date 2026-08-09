package com.example.hello_android.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hello_android.R
import com.example.hello_android.ui.components.HelloButton
import com.example.hello_android.ui.components.HelloButtonStyle
import com.example.hello_android.ui.components.HelloScreenTitle
import com.example.hello_android.ui.components.HelloSystemBars
import com.example.hello_android.ui.components.HelloTopBar

@Composable
fun MicrophonePermissionScreen(
    onBack: () -> Unit,
    onShowInstructions: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HelloSystemBars(darkStatusIcons = true)
    Box(modifier = modifier.fillMaxSize()) {
        HelloTopBar(
            showBack = true,
            onBack = onBack,
            modifier = Modifier.statusBarsPadding(),
        )
        HelloScreenTitle(
            title = "마이크를 켜주세요",
            description = "곧 마이크를 물어보면 [허용]을 눌러주세요",
            modifier = Modifier.offset(y = 120.dp),
        )
        Image(
            painter = painterResource(R.drawable.onboarding_microphone_permission),
            contentDescription = "마이크 권한 허용 안내",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 220.dp)
                .size(width = 260.dp, height = 140.dp),
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
                text = "거부 시 마이크 켜는 방법",
                onClick = onShowInstructions,
                style = HelloButtonStyle.Secondary,
            )
            HelloButton(text = "다음", onClick = onNext)
        }
    }
}
