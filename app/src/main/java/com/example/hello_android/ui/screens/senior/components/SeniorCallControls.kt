package com.example.hello_android.ui.screens.senior.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hello_android.R
import com.example.hello_android.ui.theme.helloColors
import com.example.hello_android.ui.theme.helloTypography

enum class SeniorCompactMode { Camera, ScreenShare }

@Composable
fun SeniorVoiceCallControls(
    onCamera: () -> Unit,
    onScreenShare: () -> Unit,
    onHangUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.helloColors
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp)
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(colors.surfaceDefault)
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            SeniorRoundFunctionButton(
                icon = R.drawable.ic_videocam_disabled,
                label = stringResource(R.string.senior_show_camera),
                iconColor = colors.iconDefault,
                showDisabledSlash = true,
                onClick = onCamera,
            )
            SeniorRoundFunctionButton(
                icon = R.drawable.ic_screen_share_large,
                label = stringResource(R.string.senior_show_my_screen),
                iconColor = colors.iconDefault,
                showDisabledSlash = true,
                onClick = onScreenShare,
            )
            SeniorRoundFunctionButton(
                icon = R.drawable.ic_volume_up,
                label = stringResource(R.string.senior_speaker),
                iconColor = colors.statusOn,
                onClick = null,
            )
        }
        SeniorHangUpButton(onClick = onHangUp)
    }
}

@Composable
fun SeniorCompactCallControls(
    mode: SeniorCompactMode,
    onReturnToVoice: () -> Unit,
    onHangUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.helloColors
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(104.dp)
            .background(colors.surfaceDark.copy(alpha = 0.8f))
            .padding(horizontal = 40.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(50.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SeniorCompactFunctionButton(
            icon = if (mode == SeniorCompactMode.Camera) R.drawable.ic_videocam else R.drawable.ic_screen_share,
            label = stringResource(
                if (mode == SeniorCompactMode.Camera) R.string.senior_camera else R.string.senior_my_screen,
            ),
            iconSize = if (mode == SeniorCompactMode.Camera) 36.dp else 24.dp,
            onClick = onReturnToVoice,
        )
        SeniorHangUpButton(onClick = onHangUp)
        SeniorCompactFunctionButton(
            icon = R.drawable.ic_volume_up,
            label = stringResource(R.string.senior_speaker),
            onClick = null,
        )
    }
}

@Composable
private fun SeniorRoundFunctionButton(
    @DrawableRes icon: Int,
    label: String,
    iconColor: Color,
    onClick: (() -> Unit)?,
    showDisabledSlash: Boolean = false,
) {
    val colors = MaterialTheme.helloColors
    Column(
        modifier = Modifier.then(
            if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(1.dp, colors.borderSubtle, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(36.dp),
            )
            if (showDisabledSlash) {
                Image(
                    painter = painterResource(R.drawable.ic_disabled_slash),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp),
                )
            }
        }
        Text(
            text = label,
            style = MaterialTheme.helloTypography.label1,
            color = colors.textTertiary,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun SeniorCompactFunctionButton(
    @DrawableRes icon: Int,
    label: String,
    iconSize: androidx.compose.ui.unit.Dp = 36.dp,
    onClick: (() -> Unit)?,
) {
    val colors = MaterialTheme.helloColors
    Column(
        modifier = Modifier.then(
            if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = label,
            tint = colors.statusOn,
            modifier = Modifier.size(iconSize),
        )
        Text(
            text = label,
            style = MaterialTheme.helloTypography.label1,
            color = colors.textOnColor,
        )
    }
}

@Composable
private fun SeniorHangUpButton(onClick: () -> Unit) {
    val colors = MaterialTheme.helloColors
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(colors.statusError)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_call_end),
            contentDescription = stringResource(R.string.senior_hang_up),
            tint = colors.iconOnColor,
            modifier = Modifier
                .width(26.614.dp)
                .height(26.667.dp)
                .rotate(135f),
        )
    }
}
