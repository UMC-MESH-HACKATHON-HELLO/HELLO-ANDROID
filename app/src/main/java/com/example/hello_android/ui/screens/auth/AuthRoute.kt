package com.example.hello_android.ui.screens.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.hello_android.R

private enum class AuthStep { Login, Email, Code, Password, Success }

@Composable
fun AuthRoute(
    onBackToEntry: () -> Unit,
    onAuthenticated: () -> Unit,
) {
    var step by rememberSaveable { mutableStateOf(AuthStep.Login) }
    var email by rememberSaveable { mutableStateOf("") }
    var code by rememberSaveable { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var emailError by rememberSaveable { mutableStateOf<String?>(null) }
    var codeError by rememberSaveable { mutableStateOf<String?>(null) }
    var codeNotice by rememberSaveable { mutableStateOf<String?>(null) }
    var passwordError by rememberSaveable { mutableStateOf<String?>(null) }
    val invalidEmailMessage = stringResource(R.string.auth_error_invalid_email)
    val invalidCodeMessage = stringResource(R.string.auth_error_invalid_code)
    val codeResentMessage = stringResource(R.string.auth_notice_code_resent)
    val invalidPasswordMessage = stringResource(R.string.auth_error_invalid_password)

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
                emailError = invalidEmailMessage
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
                codeNotice = codeResentMessage
            },
            onNext = {
                if (code.length == 6) step = AuthStep.Password
                else codeError = invalidCodeMessage
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
                    password.any { it in 'A'..'Z' || it in 'a'..'z' } &&
                    password.any(Char::isDigit) &&
                    password.any { !it.isLetterOrDigit() }
                if (valid) step = AuthStep.Success
                else passwordError = invalidPasswordMessage
            },
            errorMessage = passwordError,
        )
        AuthStep.Success -> SignUpSuccessScreen(onHome = onAuthenticated)
    }
}
