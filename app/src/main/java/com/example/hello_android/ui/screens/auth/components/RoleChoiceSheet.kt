package com.example.hello_android.ui.screens.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.hello_android.R
import com.example.hello_android.ui.components.HelloButton
import com.example.hello_android.ui.components.HelloButtonStyle
import com.example.hello_android.ui.theme.helloColors
import com.example.hello_android.ui.theme.helloTypography

@Composable
fun RoleChoiceSheet(
    onDismiss: () -> Unit,
    onKakao: () -> Unit,
    onEmail: () -> Unit,
) {
    val colors = MaterialTheme.helloColors
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false,
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.surfaceDark.copy(alpha = 0.3f))
                .clickable(onClick = onDismiss),
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(296.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(colors.surfaceDefault)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {},
                    )
                    .navigationBarsPadding()
                    .padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 40.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = stringResource(R.string.auth_sign_up_method_title),
                        style = MaterialTheme.helloTypography.body2Strong,
                        color = colors.textPrimary,
                    )
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clickable(onClick = onDismiss),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_close),
                            contentDescription = stringResource(R.string.action_close),
                            modifier = Modifier.size(16.dp),
                        )
                    }
                }
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    KakaoButton(text = stringResource(R.string.auth_kakao_start), onClick = onKakao)
                    HelloButton(
                        text = stringResource(R.string.auth_email_start),
                        onClick = onEmail,
                        style = HelloButtonStyle.Secondary,
                        leadingContent = {
                            Image(
                                painter = painterResource(R.drawable.ic_email),
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                            )
                        },
                    )
                }
            }
        }
    }
}
