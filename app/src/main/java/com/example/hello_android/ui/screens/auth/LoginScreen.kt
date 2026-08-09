package com.example.hello_android.ui.screens.auth

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.hello_android.ui.components.HelloButton
import com.example.hello_android.ui.components.HelloButtonStyle
import com.example.hello_android.ui.components.HelloScreenTitle
import com.example.hello_android.ui.components.HelloSystemBars
import com.example.hello_android.ui.components.HelloTopBar
import com.example.hello_android.ui.screens.auth.components.HelloUnderlineTextField
import com.example.hello_android.ui.screens.auth.components.KakaoButton
import com.example.hello_android.ui.screens.auth.components.PasswordVisibilityButton
import com.example.hello_android.ui.screens.auth.components.RoleChoiceSheet
import com.example.hello_android.ui.screens.auth.components.dismissKeyboardOnTap
import com.example.hello_android.ui.theme.helloColors
import com.example.hello_android.ui.theme.helloTypography

@Composable
fun LoginScreen(
    onBack: () -> Unit,
    onLogin: (email: String, password: String) -> Unit,
    onEmailSignUp: () -> Unit,
    onKakao: () -> Unit,
    modifier: Modifier = Modifier,
    passwordError: String? = null,
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var showSignUpSheet by rememberSaveable { mutableStateOf(false) }
    val enabled = email.isNotBlank() && password.isNotBlank()
    val colors = MaterialTheme.helloColors

    HelloSystemBars(darkStatusIcons = true)
    Box(modifier = modifier.fillMaxSize().dismissKeyboardOnTap()) {
        HelloTopBar(
            showBack = true,
            onBack = onBack,
            modifier = Modifier.statusBarsPadding(),
        )
        HelloScreenTitle(
            title = "도우미로 시작할까요?",
            description = "서비스 이용을 위해 로그인해 주세요",
            modifier = Modifier.offset(y = 120.dp),
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 230.dp)
                .width(328.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                HelloUnderlineTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "이메일",
                    placeholder = "이메일 입력",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                )
                HelloUnderlineTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "비밀번호",
                    placeholder = "비밀번호 입력",
                    errorMessage = passwordError,
                    visualTransformation = if (passwordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingContent = if (password.isNotEmpty()) {
                        { PasswordVisibilityButton(passwordVisible) { passwordVisible = !passwordVisible } }
                    } else null,
                )
            }
            HelloButton(
                text = "로그인",
                onClick = { onLogin(email, password) },
                enabled = enabled,
                style = if (enabled) HelloButtonStyle.Primary else HelloButtonStyle.Disabled,
            )
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Canvas(Modifier.weight(1f)) {
                        drawLine(
                            colors.borderSubtle,
                            start = Offset(0f, center.y),
                            end = Offset(size.width, center.y),
                            strokeWidth = 1.dp.toPx(),
                        )
                    }
                    Text(
                        text = "또는",
                        style = MaterialTheme.helloTypography.body3,
                        color = colors.textDisabled,
                    )
                    Canvas(Modifier.weight(1f)) {
                        drawLine(
                            colors.borderSubtle,
                            start = Offset(0f, center.y),
                            end = Offset(size.width, center.y),
                            strokeWidth = 1.dp.toPx(),
                        )
                    }
                }
                KakaoButton(text = "카카오로 계속하기", onClick = onKakao)
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .offset(y = (-20).dp)
                .clickable { showSignUpSheet = true }
                .padding(horizontal = 10.dp, vertical = 13.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "회원가입",
                style = MaterialTheme.helloTypography.body3,
                color = colors.textTertiary,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline,
            )
        }
        if (showSignUpSheet) {
            RoleChoiceSheet(
                onDismiss = { showSignUpSheet = false },
                onKakao = onKakao,
                onEmail = {
                    showSignUpSheet = false
                    onEmailSignUp()
                },
            )
        }
    }
}
