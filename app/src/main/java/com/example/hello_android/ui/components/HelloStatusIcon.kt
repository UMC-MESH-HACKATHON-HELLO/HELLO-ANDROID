package com.example.hello_android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hello_android.R
import com.example.hello_android.ui.theme.helloColors

enum class HelloStatusIconType { Success, Error, Alert }

@Composable
fun HelloStatusIcon(
    type: HelloStatusIconType,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.helloColors
    val background = when (type) {
        HelloStatusIconType.Success -> colors.surfaceActivated
        HelloStatusIconType.Error,
        HelloStatusIconType.Alert -> colors.surfaceBrandSubtle
    }
    Box(
        modifier = modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(background),
        contentAlignment = Alignment.Center,
    ) {
        when (type) {
            HelloStatusIconType.Success -> Image(
                painter = painterResource(R.drawable.ic_check),
                contentDescription = null,
                modifier = Modifier.size(40.dp),
            )
            HelloStatusIconType.Error -> Image(
                painter = painterResource(R.drawable.ic_status_error),
                contentDescription = null,
                modifier = Modifier.size(40.dp),
            )
            HelloStatusIconType.Alert -> Image(
                painter = painterResource(R.drawable.ic_status_alert),
                contentDescription = null,
                modifier = Modifier
                    .width(5.026.dp)
                    .height(30.dp),
            )
        }
    }
}
