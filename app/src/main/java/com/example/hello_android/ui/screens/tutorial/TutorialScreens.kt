package com.example.hello_android.ui.screens.tutorial

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hello_android.R
import com.example.hello_android.ui.components.HelloButton
import com.example.hello_android.ui.components.HelloNumberBadge
import com.example.hello_android.ui.components.HelloScreenTitle
import com.example.hello_android.ui.components.HelloSystemBars
import com.example.hello_android.ui.components.HelloTutorialNavigation
import com.example.hello_android.ui.components.HelloTutorialRole
import com.example.hello_android.ui.components.HelloTutorialRoleImage
import com.example.hello_android.ui.components.HelloTutorialTopBar

@Composable
fun TutorialIntroScreen(
    role: HelloTutorialRole,
    onClose: () -> Unit,
    onStart: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HelloSystemBars(darkStatusIcons = true)
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        HelloTutorialTopBar(
            onClose = onClose,
            modifier = Modifier.statusBarsPadding(),
        )
        HelloScreenTitle(
            title = stringResource(
                if (role == HelloTutorialRole.Senior) {
                    R.string.tutorial_senior_intro
                } else {
                    R.string.tutorial_helper_intro
                },
            ),
            modifier = Modifier
                .statusBarsPadding()
                .offset(y = 96.dp),
        )
        Box(
            modifier = Modifier
                .statusBarsPadding()
                .offset(y = 210.dp)
                .fillMaxWidth()
                .height(430.dp),
            contentAlignment = Alignment.TopCenter,
        ) {
            HelloTutorialRoleImage(
                role = role,
                modifier = Modifier.offset(y = 60.dp),
            )
        }
        HelloButton(
            text = stringResource(R.string.action_start),
            onClick = onStart,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 20.dp)
                .width(328.dp),
        )
    }
}

@Composable
fun TutorialStepScreen(
    number: Int,
    @StringRes title: Int,
    @DrawableRes image: Int,
    onClose: () -> Unit,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HelloSystemBars(darkStatusIcons = true)
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        HelloTutorialTopBar(
            onClose = onClose,
            modifier = Modifier.statusBarsPadding(),
        )
        HelloNumberBadge(
            number = number,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .statusBarsPadding()
                .offset(y = 56.dp),
        )
        HelloScreenTitle(
            title = stringResource(title),
            modifier = Modifier
                .statusBarsPadding()
                .offset(y = 96.dp),
        )
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .statusBarsPadding()
                .offset(y = 210.dp)
                .fillMaxWidth()
                .height(430.dp),
        )
        HelloTutorialNavigation(
            onPrevious = onPrevious,
            onNext = onNext,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 20.dp)
                .width(328.dp),
        )
    }
}
