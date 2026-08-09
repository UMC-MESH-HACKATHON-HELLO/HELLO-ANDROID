package com.example.hello_android.ui.screens.onboarding

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.net.toUri

@Composable
fun MicrophonePermissionRoute(
    onBack: () -> Unit,
    onFinished: () -> Unit,
) {
    val context = LocalContext.current
    var showSettingsInstructions by rememberSaveable { mutableStateOf(false) }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ) { granted ->
        if (granted) onFinished() else showSettingsInstructions = true
    }

    val requestOrContinue = remember(context, permissionLauncher) {
        {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.RECORD_AUDIO,
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                onFinished()
            } else {
                permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
            }
        }
    }

    if (showSettingsInstructions) {
        MicrophoneSettingsScreen(
            onBack = { showSettingsInstructions = false },
            onOpenSettings = {
                context.startActivity(
                    Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        "package:${context.packageName}".toUri(),
                    ),
                )
            },
            onNext = requestOrContinue,
        )
    } else {
        MicrophonePermissionScreen(
            onBack = onBack,
            onShowInstructions = { showSettingsInstructions = true },
            onNext = requestOrContinue,
        )
    }
}
