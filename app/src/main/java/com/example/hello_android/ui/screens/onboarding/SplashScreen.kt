package com.example.hello_android.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.offset
import com.example.hello_android.ui.components.HelloLogo
import com.example.hello_android.ui.components.HelloSystemBars
import com.example.hello_android.ui.theme.helloColors

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    HelloSystemBars(darkStatusIcons = false, darkNavigationIcons = false)
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.helloColors.actionPrimary),
        contentAlignment = Alignment.Center,
    ) {
        HelloLogo(
            onBrandBackground = true,
            modifier = Modifier.offset(y = (-10).dp),
        )
    }
}
