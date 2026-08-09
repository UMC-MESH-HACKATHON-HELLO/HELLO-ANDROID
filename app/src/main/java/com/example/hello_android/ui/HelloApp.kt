package com.example.hello_android.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import com.example.hello_android.ui.screens.auth.AuthRoute
import com.example.hello_android.ui.screens.onboarding.EntryScreen
import com.example.hello_android.ui.screens.onboarding.MicrophonePermissionRoute
import com.example.hello_android.ui.screens.onboarding.SplashScreen
import com.example.hello_android.ui.screens.senior.SeniorCallEndScreen
import com.example.hello_android.ui.screens.senior.SeniorCallingSummaryScreen
import com.example.hello_android.ui.screens.senior.SeniorCallingTryingScreen
import com.example.hello_android.ui.screens.senior.SeniorCameraCallScreen
import com.example.hello_android.ui.screens.senior.SeniorHomeScreen
import com.example.hello_android.ui.screens.senior.SeniorReportScreen
import com.example.hello_android.ui.screens.senior.SeniorScreenShareCallScreen
import com.example.hello_android.ui.screens.senior.SeniorVoiceCallScreen
import com.example.hello_android.ui.theme.helloColors
import kotlinx.coroutines.delay

private enum class HelloRoute {
    Splash,
    Entry,
    Microphone,
    SeniorHome,
    SeniorTrying,
    SeniorVoice,
    SeniorCamera,
    SeniorShare,
    SeniorSummary,
    SeniorEnd,
    SeniorReport,
    Auth,
}

@Composable
fun HelloApp() {
    var route by rememberSaveable { mutableStateOf(HelloRoute.Splash) }

    LaunchedEffect(route) {
        when (route) {
            HelloRoute.Splash -> {
                delay(1_200)
                route = HelloRoute.Entry
            }
            HelloRoute.SeniorTrying -> {
                delay(1_500)
                route = HelloRoute.SeniorVoice
            }
            else -> Unit
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.helloColors.surfaceDefault,
    ) {
        when (route) {
            HelloRoute.Splash -> SplashScreen()
            HelloRoute.Entry -> EntryScreen(
                onSeniorSelected = { route = HelloRoute.Microphone },
                onHelperSelected = { route = HelloRoute.Auth },
            )
            HelloRoute.Microphone -> MicrophonePermissionRoute(
                onBack = { route = HelloRoute.Entry },
                onFinished = { route = HelloRoute.SeniorHome },
            )
            HelloRoute.SeniorHome -> SeniorHomeScreen(
                onStartCall = { route = HelloRoute.SeniorTrying },
            )
            HelloRoute.SeniorTrying -> SeniorCallingTryingScreen(
                onCancel = { route = HelloRoute.SeniorHome },
            )
            HelloRoute.SeniorVoice -> SeniorVoiceCallScreen(
                onCamera = { route = HelloRoute.SeniorCamera },
                onScreenShare = { route = HelloRoute.SeniorShare },
                onHangUp = { route = HelloRoute.SeniorSummary },
            )
            HelloRoute.SeniorCamera -> SeniorCameraCallScreen(
                onReturnToVoice = { route = HelloRoute.SeniorVoice },
                onHangUp = { route = HelloRoute.SeniorSummary },
            )
            HelloRoute.SeniorShare -> SeniorScreenShareCallScreen(
                onReturnToVoice = { route = HelloRoute.SeniorVoice },
                onHangUp = { route = HelloRoute.SeniorSummary },
            )
            HelloRoute.SeniorSummary -> SeniorCallingSummaryScreen(
                onNext = { route = HelloRoute.SeniorEnd },
            )
            HelloRoute.SeniorEnd -> SeniorCallEndScreen(
                onReport = { route = HelloRoute.SeniorReport },
                onHome = { route = HelloRoute.SeniorHome },
            )
            HelloRoute.SeniorReport -> SeniorReportScreen(
                onBack = { route = HelloRoute.SeniorEnd },
                onSubmit = { route = HelloRoute.SeniorHome },
            )
            HelloRoute.Auth -> AuthRoute(
                onBackToEntry = { route = HelloRoute.Entry },
                onAuthenticated = { route = HelloRoute.Entry },
            )
        }
    }
}
