package com.example.hello_android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hello_android.R

@Composable
fun HelloLogo(
    modifier: Modifier = Modifier,
    onBrandBackground: Boolean = false,
    compact: Boolean = false,
) {
    Image(
        painter = painterResource(
            if (onBrandBackground) R.drawable.hello_logo_white else R.drawable.hello_logo_orange,
        ),
        contentDescription = "여보세요",
        modifier = modifier.then(
            if (compact) Modifier.width(97.dp).height(19.dp)
            else if (onBrandBackground) Modifier.width(205.dp).height(40.dp)
            else Modifier.width(209.dp).height(40.dp),
        ),
        contentScale = ContentScale.Fit,
    )
}
