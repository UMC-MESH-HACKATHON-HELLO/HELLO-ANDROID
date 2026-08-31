package com.example.hello_android.ui.screens.senior

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hello_android.R
import com.example.hello_android.ui.components.HelloSystemBars
import com.example.hello_android.ui.components.HelloDimGradient
import com.example.hello_android.ui.screens.senior.components.SeniorCallHeader
import com.example.hello_android.ui.screens.senior.components.SeniorCompactCallControls
import com.example.hello_android.ui.screens.senior.components.SeniorCompactMode
import com.example.hello_android.ui.screens.senior.components.SeniorVoiceCallControls
import com.example.hello_android.ui.theme.helloColors
import com.example.hello_android.ui.theme.helloTypography
import kotlinx.coroutines.delay

private const val CameraControlsAutoHideMillis = 3_000L

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
                text = stringResource(R.string.senior_anonymous_helper),
                style = MaterialTheme.helloTypography.title1,
                color = colors.textOnColor,
                textAlign = TextAlign.Center,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    stringResource(R.string.senior_voice_call),
                    style = MaterialTheme.helloTypography.body3Medium,
                    color = colors.textOnColor,
                )
                Text(
                    stringResource(R.string.senior_call_duration_placeholder),
                    style = MaterialTheme.helloTypography.body3,
                    color = colors.textOnColor,
                )
            }
            Text(
                text = stringResource(R.string.senior_ai_monitoring),
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
) {
    HelloSystemBars(darkStatusIcons = false)
    val colors = MaterialTheme.helloColors
    var controlsVisible by rememberSaveable { mutableStateOf(true) }
    val cameraInteractionSource = remember { MutableInteractionSource() }

    LaunchedEffect(controlsVisible) {
        if (controlsVisible) {
            delay(CameraControlsAutoHideMillis)
            controlsVisible = false
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.surfaceCameraFallback),
    ) {
        Image(
            painter = painterResource(R.drawable.senior_camera_reference),
            contentDescription = stringResource(R.string.senior_camera_preview_description),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = cameraInteractionSource,
                    indication = null,
                ) {
                    controlsVisible = !controlsVisible
                },
        ) {}
        if (controlsVisible) {
            HelloDimGradient(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .height(258.dp),
            )
            SeniorCallHeader(
                mode = stringResource(R.string.senior_video_call),
                title = stringResource(R.string.senior_camera_active_title),
                description = stringResource(R.string.senior_camera_active_description),
                modifier = Modifier.offset(y = 54.dp),
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
            mode = stringResource(R.string.senior_screen_share),
            title = stringResource(R.string.senior_screen_share_active_title),
            description = stringResource(R.string.senior_screen_share_active_description),
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
