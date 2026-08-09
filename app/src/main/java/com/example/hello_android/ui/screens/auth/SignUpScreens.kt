package com.example.hello_android.ui.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.hello_android.ui.components.HelloButton
import com.example.hello_android.ui.components.HelloButtonStyle
import com.example.hello_android.ui.components.HelloScreenTitle
import com.example.hello_android.ui.components.HelloSystemBars
import com.example.hello_android.ui.components.HelloTopBar
import com.example.hello_android.ui.components.HelloStatusIcon
import com.example.hello_android.ui.components.HelloStatusIconType
import com.example.hello_android.ui.screens.auth.components.HelloUnderlineTextField
import com.example.hello_android.ui.screens.auth.components.PasswordVisibilityButton
import com.example.hello_android.ui.screens.auth.components.dismissKeyboardOnTap
import com.example.hello_android.ui.theme.helloColors
import com.example.hello_android.ui.theme.helloTypography

@Composable
fun SignUpEmailScreen(
    email: String,
    onEmailChange: (String) -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
) {
    var fieldFocused by rememberSaveable { mutableStateOf(false) }

    HelloSystemBars(darkStatusIcons = true)
    Box(modifier = modifier.fillMaxSize().dismissKeyboardOnTap()) {
        HelloTopBar(showBack = true, onBack = onBack, modifier = Modifier.statusBarsPadding())
        HelloScreenTitle(
            title = "이메일을 입력해주세요",
            description = if (fieldFocused || errorMessage != null) {
                "가입을 위해 필요한 정보를 입력해 주세요."
            } else {
                "서비스에 사용할 이메일 주소예요"
            },
            modifier = Modifier.offset(y = 120.dp),
        )
        HelloUnderlineTextField(
            value = email,
            onValueChange = onEmailChange,
            label = "이메일",
            placeholder = "이메일 입력",
            errorMessage = errorMessage,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onFocusChange = { fieldFocused = it },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 230.dp)
                .width(328.dp),
        )
        AdaptiveBottomButton(
            text = "다음",
            enabled = email.isNotBlank(),
            onClick = onNext,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}

@Composable
fun SignUpCodeScreen(
    email: String,
    code: String,
    onCodeChange: (String) -> Unit,
    onBack: () -> Unit,
    onResend: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    resendMessage: String? = null,
    timer: String = "00:45",
) {
    HelloSystemBars(darkStatusIcons = true)
    Box(modifier = modifier.fillMaxSize().dismissKeyboardOnTap()) {
        HelloTopBar(showBack = true, onBack = onBack, modifier = Modifier.statusBarsPadding())
        HelloScreenTitle(
            title = "인증코드를 입력해주세요",
            description = "${email}으로 코드가 전송되었어요.",
            modifier = Modifier.offset(y = 120.dp),
        )
        HelloUnderlineTextField(
            value = code,
            onValueChange = { onCodeChange(it.filter(Char::isDigit).take(6)) },
            label = "인증코드",
            placeholder = "인증코드 6자리",
            errorMessage = errorMessage,
            supportingMessage = resendMessage,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            trailingContent = {
                Text(
                    text = timer,
                    style = MaterialTheme.helloTypography.label2,
                    color = MaterialTheme.helloColors.textTertiary,
                )
            },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 230.dp)
                .width(328.dp),
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .imePadding()
                .navigationBarsPadding()
                .padding(bottom = 20.dp)
                .width(328.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Box(
                modifier = Modifier
                    .padding(vertical = 3.dp)
                    .clickable(onClick = onResend),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "재발송",
                    style = MaterialTheme.helloTypography.body3,
                    color = MaterialTheme.helloColors.textTertiary,
                    textDecoration = TextDecoration.Underline,
                )
            }
            HelloButton(
                text = "다음",
                onClick = onNext,
                enabled = code.length == 6,
                style = if (code.length == 6) HelloButtonStyle.Primary else HelloButtonStyle.Disabled,
            )
        }
    }
}

@Composable
fun SignUpPasswordScreen(
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    onVisibilityChange: (Boolean) -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
) {
    HelloSystemBars(darkStatusIcons = true)
    Box(modifier = modifier.fillMaxSize().dismissKeyboardOnTap()) {
        HelloTopBar(showBack = true, onBack = onBack, modifier = Modifier.statusBarsPadding())
        HelloScreenTitle(
            title = "비밀번호를 설정해주세요",
            description = "가입을 위해 필요한 정보를 입력해 주세요.",
            modifier = Modifier.offset(y = 120.dp),
        )
        HelloUnderlineTextField(
            value = password,
            onValueChange = { onPasswordChange(it.take(16)) },
            label = "비밀번호",
            placeholder = "비밀번호 입력",
            errorMessage = errorMessage,
            supportingMessage = if (errorMessage == null) "영문, 숫자, 특수문자 포함 8~16자" else null,
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingContent = if (password.isNotEmpty()) {
                { PasswordVisibilityButton(passwordVisible) { onVisibilityChange(!passwordVisible) } }
            } else null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 230.dp)
                .width(328.dp),
        )
        AdaptiveBottomButton(
            text = "다음",
            enabled = password.isNotEmpty(),
            onClick = onNext,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}

@Composable
fun SignUpSuccessScreen(
    onHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HelloSystemBars(darkStatusIcons = true)
    Box(modifier = modifier.fillMaxSize()) {
        HelloScreenTitle(
            title = "가입이 완료되었어요",
            modifier = Modifier.offset(y = 120.dp),
        )
        HelloStatusIcon(
            type = HelloStatusIconType.Success,
            modifier = Modifier.align(Alignment.Center),
        )
        HelloButton(
            text = "홈으로",
            onClick = onHome,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 20.dp)
                .width(328.dp),
        )
    }
}

@Composable
private fun AdaptiveBottomButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HelloButton(
        text = text,
        onClick = onClick,
        enabled = enabled,
        style = if (enabled) HelloButtonStyle.Primary else HelloButtonStyle.Disabled,
        modifier = modifier
            .imePadding()
            .navigationBarsPadding()
            .padding(bottom = 20.dp)
            .width(328.dp),
    )
}
