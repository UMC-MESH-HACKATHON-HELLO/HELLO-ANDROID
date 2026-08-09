package com.example.hello_android.ui.screens.senior

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hello_android.R
import com.example.hello_android.ui.components.HelloButton
import com.example.hello_android.ui.components.HelloScreenTitle
import com.example.hello_android.ui.components.HelloSystemBars
import com.example.hello_android.ui.components.HelloTopBar
import com.example.hello_android.ui.screens.senior.components.SeniorReportOption

enum class SeniorReportReason(@param:StringRes val labelRes: Int) {
    Financial(R.string.senior_report_financial),
    PersonalInformation(R.string.senior_report_personal_information),
    RudeLanguage(R.string.senior_report_rude_language),
    Unhelpful(R.string.senior_report_unhelpful),
    Other(R.string.senior_report_other),
}

@Composable
fun SeniorReportScreen(
    onBack: () -> Unit,
    onSubmit: (Set<SeniorReportReason>) -> Unit,
    modifier: Modifier = Modifier,
) {
    val selections = remember {
        mutableStateListOf(true, false, false, false, false)
    }
    val reasons = SeniorReportReason.entries
    val hasSelection = selections.any { it }

    HelloSystemBars(darkStatusIcons = true)
    Box(modifier = modifier.fillMaxSize()) {
        HelloTopBar(
            showBack = true,
            onBack = onBack,
            modifier = Modifier.statusBarsPadding(),
        )
        HelloScreenTitle(
            title = stringResource(R.string.senior_report_title),
            description = stringResource(R.string.senior_report_description),
            modifier = Modifier.offset(y = 120.dp),
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 230.dp)
                .width(328.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            reasons.forEachIndexed { index, reason ->
                SeniorReportOption(
                    text = stringResource(reason.labelRes),
                    selected = selections[index],
                    onClick = { selections[index] = !selections[index] },
                )
            }
        }
        HelloButton(
            text = stringResource(R.string.senior_report_submit),
            onClick = {
                onSubmit(
                    reasons
                        .filterIndexed { index, _ -> selections[index] }
                        .toSet(),
                )
            },
            enabled = hasSelection,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 20.dp)
                .width(328.dp),
        )
    }
}
