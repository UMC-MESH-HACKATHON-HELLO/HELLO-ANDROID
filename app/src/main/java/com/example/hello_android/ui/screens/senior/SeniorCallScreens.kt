package com.example.hello_android.ui.screens.senior

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hello_android.R
import com.example.hello_android.ui.components.HelloSystemBars
import com.example.hello_android.ui.screens.senior.components.SeniorCallHeader
import com.example.hello_android.ui.screens.senior.components.SeniorCompactCallControls
import com.example.hello_android.ui.screens.senior.components.SeniorCompactMode
import com.example.hello_android.ui.screens.senior.components.SeniorVoiceCallControls
import com.example.hello_android.ui.theme.helloColors
import com.example.hello_android.ui.theme.helloTypography

@Composable
fun SeniorVoiceCallScreen(
    onCamera: () -> Unit,
    onScreenShare: () -> Unit,
    onHangUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HelloSystemBars(darkStatusIcons = false)
    val colors = MaterialTheme.helloColors
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.surfaceBrand),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 120.dp)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "익명의 도우미",
                style = MaterialTheme.helloTypography.title1,
                color = colors.textOnColor,
                textAlign = TextAlign.Center,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("음성통화", style = MaterialTheme.helloTypography.body3Medium, color = colors.textOnColor)
                Text("00:03", style = MaterialTheme.helloTypography.body3, color = colors.textOnColor)
            }
            Text(
                text = "AI가 안전을 확인 중이에요",
                style = MaterialTheme.helloTypography.body3Medium,
                color = colors.textOnColor,
                modifier = Modifier.padding(top = 30.dp),
            )
        }
        SeniorVoiceCallControls(
            onCamera = onCamera,
            onScreenShare = onScreenShare,
            onHangUp = onHangUp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding(),
        )
    }
}

@Composable
fun SeniorCameraCallScreen(
    onReturnToVoice: () -> Unit,
    onHangUp: () -> Unit,
    modifier: Modifier = Modifier,
    immersive: Boolean = false,
) {
    HelloSystemBars(darkStatusIcons = false)
    val colors = MaterialTheme.helloColors
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.surfaceCameraFallback),
    ) {
        Image(
            painter = painterResource(R.drawable.senior_camera_reference),
            contentDescription = "카메라 미리보기",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(258.dp)
                .align(Alignment.TopCenter)
                .background(
                    Brush.verticalGradient(
                        listOf(colors.surfaceDark, colors.surfaceDark.copy(alpha = 0f)),
                    ),
                ),
        )
        if (!immersive) {
            SeniorCallHeader(
                mode = "영상통화",
                title = "카메라로 보여주는 중",
                description = "주변을 비추면 도우미가 봐요",
                modifier = Modifier.offset(y = 86.dp),
            )
            SeniorCompactCallControls(
                mode = SeniorCompactMode.Camera,
                onReturnToVoice = onReturnToVoice,
                onHangUp = onHangUp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .navigationBarsPadding(),
            )
        }
    }
}

@Composable
fun SeniorScreenShareCallScreen(
    onReturnToVoice: () -> Unit,
    onHangUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HelloSystemBars(darkStatusIcons = false)
    val colors = MaterialTheme.helloColors
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.surfaceDark),
    ) {
        SeniorCallHeader(
            mode = "화면공유",
            title = "내 화면을 보여주는 중",
            description = "다른 앱을 열어도 계속 보여요",
            modifier = Modifier.offset(y = 86.dp),
        )
        SeniorCompactCallControls(
            mode = SeniorCompactMode.ScreenShare,
            onReturnToVoice = onReturnToVoice,
            onHangUp = onHangUp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding(),
        )
    }
}
