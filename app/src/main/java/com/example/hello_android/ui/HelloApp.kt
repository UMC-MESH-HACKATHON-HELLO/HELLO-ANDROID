package com.example.hello_android.ui

import androidx.activity.compose.BackHandler
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.fillMaxSize
import com.example.hello_android.R
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
import com.example.hello_android.ui.screens.tutorial.TutorialRoute
import com.example.hello_android.ui.components.HelloTutorialRole
import com.example.hello_android.ui.theme.helloColors
import kotlinx.coroutines.delay

private const val TutorialPreferencesName = "hello_tutorial_preferences"
private const val SeniorTutorialSeenKey = "senior_tutorial_seen"
private const val HelperTutorialSeenKey = "helper_tutorial_seen"

private enum class HelloRoute {
    Splash,
    Entry,
    Microphone,
    SeniorTutorial,
    HelperTutorial,
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
    val context = LocalContext.current
    val tutorialPreferences = remember(context) {
        context.applicationContext.getSharedPreferences(
            TutorialPreferencesName,
            android.content.Context.MODE_PRIVATE,
        )
    }

    BackHandler(enabled = route != HelloRoute.Splash && route != HelloRoute.Entry) {
        route = when (route) {
            HelloRoute.Microphone,
            HelloRoute.SeniorTutorial,
            HelloRoute.HelperTutorial,
            HelloRoute.SeniorHome,
            HelloRoute.Auth -> HelloRoute.Entry
            HelloRoute.SeniorTrying,
            HelloRoute.SeniorVoice -> HelloRoute.SeniorHome
            HelloRoute.SeniorCamera,
            HelloRoute.SeniorShare -> HelloRoute.SeniorVoice
            HelloRoute.SeniorSummary -> HelloRoute.SeniorHome
            HelloRoute.SeniorEnd -> HelloRoute.SeniorSummary
            HelloRoute.SeniorReport -> HelloRoute.SeniorEnd
            HelloRoute.Splash,
            HelloRoute.Entry -> route
        }
    }

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
                onSeniorSelected = {
                    route = if (tutorialPreferences.getBoolean(SeniorTutorialSeenKey, false)) {
                        HelloRoute.Microphone
                    } else {
                        HelloRoute.SeniorTutorial
                    }
                },
                onHelperSelected = {
                    route = if (tutorialPreferences.getBoolean(HelperTutorialSeenKey, false)) {
                        HelloRoute.Auth
                    } else {
                        HelloRoute.HelperTutorial
                    }
                },
            )
            HelloRoute.SeniorTutorial -> TutorialRoute(
                role = HelloTutorialRole.Senior,
                onBack = { route = HelloRoute.Entry },
                onFinished = {
                    tutorialPreferences.edit().putBoolean(SeniorTutorialSeenKey, true).apply()
                    route = HelloRoute.Microphone
                },
            )
            HelloRoute.HelperTutorial -> TutorialRoute(
                role = HelloTutorialRole.Helper,
                onBack = { route = HelloRoute.Entry },
                onFinished = {
                    tutorialPreferences.edit().putBoolean(HelperTutorialSeenKey, true).apply()
                    route = HelloRoute.Auth
                },
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
                summaries = listOf(
                    stringResource(R.string.senior_summary_sample_question),
                    stringResource(R.string.senior_summary_sample_answer),
                    stringResource(R.string.senior_summary_sample_answer),
                    stringResource(R.string.senior_summary_sample_answer),
                ),
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
