package com.example.hello_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.SystemBarStyle
import com.example.hello_android.ui.HelloApp
import com.example.hello_android.ui.theme.Hello_AndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = android.graphics.Color.TRANSPARENT,
                darkScrim = android.graphics.Color.TRANSPARENT,
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = android.graphics.Color.TRANSPARENT,
                darkScrim = android.graphics.Color.TRANSPARENT,
            ),
        )
        setContent {
            Hello_AndroidTheme {
                HelloApp()
            }
        }
    }
}
