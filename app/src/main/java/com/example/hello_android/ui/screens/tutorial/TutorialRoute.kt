package com.example.hello_android.ui.screens.tutorial

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import com.example.hello_android.R
import com.example.hello_android.ui.components.HelloTutorialRole

private data class TutorialPage(
    val number: Int,
    @param:StringRes val title: Int,
    @param:DrawableRes val image: Int,
)

private val seniorPages = listOf(
    TutorialPage(1, R.string.tutorial_senior_01, R.drawable.tutorial_senior_01),
    TutorialPage(2, R.string.tutorial_senior_02, R.drawable.tutorial_senior_02),
    TutorialPage(3, R.string.tutorial_senior_03, R.drawable.tutorial_senior_03),
    TutorialPage(4, R.string.tutorial_senior_04, R.drawable.tutorial_senior_04),
    TutorialPage(5, R.string.tutorial_senior_05, R.drawable.tutorial_senior_05),
    TutorialPage(6, R.string.tutorial_senior_06, R.drawable.tutorial_senior_06),
    TutorialPage(7, R.string.tutorial_senior_07, R.drawable.tutorial_senior_07),
    TutorialPage(8, R.string.tutorial_senior_08, R.drawable.tutorial_senior_08),
    TutorialPage(9, R.string.tutorial_senior_09, R.drawable.tutorial_senior_09),
    TutorialPage(10, R.string.tutorial_senior_10, R.drawable.tutorial_senior_10),
    TutorialPage(11, R.string.tutorial_senior_11, R.drawable.tutorial_senior_11),
)

private val helperPages = listOf(
    TutorialPage(1, R.string.tutorial_helper_01, R.drawable.tutorial_helper_01),
    TutorialPage(2, R.string.tutorial_helper_02, R.drawable.tutorial_helper_02),
    TutorialPage(3, R.string.tutorial_helper_03, R.drawable.tutorial_helper_03),
    TutorialPage(4, R.string.tutorial_helper_04, R.drawable.tutorial_helper_04),
    TutorialPage(5, R.string.tutorial_helper_05, R.drawable.tutorial_helper_05),
    TutorialPage(6, R.string.tutorial_helper_06, R.drawable.tutorial_helper_06),
)

@Composable
fun TutorialRoute(
    role: HelloTutorialRole,
    onBack: () -> Unit,
    onFinished: () -> Unit,
) {
    val pages = if (role == HelloTutorialRole.Senior) seniorPages else helperPages
    var pageIndex by rememberSaveable(role) { mutableIntStateOf(-1) }

    BackHandler {
        if (pageIndex == -1) {
            onBack()
        } else if (pageIndex == 0) {
            pageIndex = -1
        } else {
            pageIndex -= 1
        }
    }

    if (pageIndex == -1) {
        TutorialIntroScreen(
            role = role,
            onClose = onFinished,
            onStart = { pageIndex = 0 },
        )
    } else {
        val page = pages[pageIndex]
        TutorialStepScreen(
            number = page.number,
            title = page.title,
            image = page.image,
            onClose = onFinished,
            onPrevious = {
                if (pageIndex == 0) pageIndex = -1 else pageIndex -= 1
            },
            onNext = {
                if (pageIndex == pages.lastIndex) onFinished() else pageIndex += 1
            },
        )
    }
}
