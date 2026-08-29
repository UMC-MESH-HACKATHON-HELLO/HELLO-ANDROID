package com.example.hello_android.ui.screens.auth.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hello_android.R

@Composable
fun PasswordVisibilityButton(
    visible: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(
                if (visible) R.drawable.ic_visibility else R.drawable.ic_visibility_off,
            ),
            contentDescription = stringResource(
                if (visible) R.string.auth_password_hide else R.string.auth_password_show,
            ),
            modifier = Modifier.size(16.dp),
        )
    }
}
