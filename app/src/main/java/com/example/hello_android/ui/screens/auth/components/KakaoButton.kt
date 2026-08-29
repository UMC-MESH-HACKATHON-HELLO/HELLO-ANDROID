package com.example.hello_android.ui.screens.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hello_android.R
import com.example.hello_android.ui.theme.helloColors
import com.example.hello_android.ui.theme.helloTypography

@Composable
fun KakaoButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.helloColors
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colors.surfaceKakao)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_kakao_logo),
            contentDescription = null,
            modifier = Modifier.size(18.dp),
        )
        Text(
            text = text,
            style = MaterialTheme.helloTypography.label1Medium,
            color = colors.textSecondary,
        )
    }
}
