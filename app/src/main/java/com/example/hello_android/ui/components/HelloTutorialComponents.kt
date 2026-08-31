package com.example.hello_android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hello_android.R
import com.example.hello_android.ui.theme.helloColors
import com.example.hello_android.ui.theme.helloTypography

enum class HelloTutorialRole {
    Senior,
    Helper,
}

enum class HelloNumberBadgeScale(val size: Dp) {
    Small(24.dp),
    Large(32.dp),
}

@Composable
fun HelloTutorialTopBar(
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
    ) {
        Image(
            painter = painterResource(R.drawable.tutorial_close_icon),
            contentDescription = stringResource(R.string.action_close),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x = (-16).dp)
                .size(24.dp)
                .clickable(onClick = onClose),
        )
    }
}

@Composable
fun HelloNumberBadge(
    number: Int,
    modifier: Modifier = Modifier,
    scale: HelloNumberBadgeScale = HelloNumberBadgeScale.Small,
) {
    Box(
        modifier = modifier
            .size(scale.size)
            .clip(CircleShape)
            .background(MaterialTheme.helloColors.surfaceBrandSubtle),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = number.toString(),
            style = if (scale == HelloNumberBadgeScale.Small) {
                MaterialTheme.helloTypography.body3Medium
            } else {
                MaterialTheme.helloTypography.title4
            },
            color = MaterialTheme.helloColors.textPrimary,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun HelloTutorialRoleImage(
    role: HelloTutorialRole,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(
            if (role == HelloTutorialRole.Senior) {
                R.drawable.tutorial_senior_intro
            } else {
                R.drawable.tutorial_helper_intro
            },
        ),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = modifier.size(width = 280.dp, height = 268.dp),
    )
}

@Composable
fun HelloTutorialPointIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.tutorial_point_icon),
        contentDescription = null,
        modifier = modifier.size(50.dp),
    )
}

@Composable
fun HelloTutorialNavigation(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
    ) {
        HelloButton(
            text = stringResource(R.string.action_previous),
            onClick = onPrevious,
            style = HelloButtonStyle.Secondary,
            scale = HelloButtonScale.Small,
        )
        Box(modifier = Modifier.width(10.dp))
        HelloButton(
            text = stringResource(R.string.action_next),
            onClick = onNext,
            modifier = Modifier.width(158.dp),
            style = HelloButtonStyle.Primary,
            scale = HelloButtonScale.Small,
        )
    }
}
