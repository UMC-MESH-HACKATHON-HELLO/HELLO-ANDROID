package com.example.hello_android.ui.screens.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

private enum class AuthStep { Login, Email, Code, Password, Success }

@Composable
fun AuthRoute(
    onBackToEntry: () -> Unit,
    onAuthenticated: () -> Unit,
) {
    var step by rememberSaveable { mutableStateOf(AuthStep.Login) }
    var email by rememberSaveable { mutableStateOf("") }
    var code by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var emailError by rememberSaveable { mutableStateOf<String?>(null) }
    var codeError by rememberSaveable { mutableStateOf<String?>(null) }
    var codeNotice by rememberSaveable { mutableStateOf<String?>(null) }
    var passwordError by rememberSaveable { mutableStateOf<String?>(null) }

    when (step) {
        AuthStep.Login -> LoginScreen(
            onBack = onBackToEntry,
            onLogin = { _, _ -> onAuthenticated() },
            onEmailSignUp = { step = AuthStep.Email },
            onKakao = onAuthenticated,
        )
        AuthStep.Email -> SignUpEmailScreen(
            email = email,
            onEmailChange = {
                email = it
                emailError = null
            },
            onBack = { step = AuthStep.Login },
            onNext = {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    step = AuthStep.Code
                } else {
                emailError = "유효한 이메일 양식이 아닙니다."
                }
            },
            errorMessage = emailError,
        )
        AuthStep.Code -> SignUpCodeScreen(
            email = email,
            code = code,
            onCodeChange = {
                code = it
                codeError = null
                codeNotice = null
            },
            onBack = { step = AuthStep.Email },
            onResend = {
                codeError = null
                codeNotice = "인증코드가 재발송되었습니다."
            },
            onNext = {
                if (code.length == 6) step = AuthStep.Password
                else codeError = "인증코드가 일치하지 않습니다."
            },
            errorMessage = codeError,
            resendMessage = codeNotice,
        )
        AuthStep.Password -> SignUpPasswordScreen(
            password = password,
            onPasswordChange = {
                password = it
                passwordError = null
            },
            passwordVisible = passwordVisible,
            onVisibilityChange = { passwordVisible = it },
            onBack = { step = AuthStep.Code },
            onNext = {
                val valid = password.length in 8..16 &&
                    password.any(Char::isLetter) &&
                    password.any(Char::isDigit) &&
                    password.any { !it.isLetterOrDigit() }
                if (valid) step = AuthStep.Success
                else passwordError = "8~16자의 영문, 숫자, 특수문자를 포함해주세요."
            },
            errorMessage = passwordError,
        )
        AuthStep.Success -> SignUpSuccessScreen(onHome = onAuthenticated)
    }
}
