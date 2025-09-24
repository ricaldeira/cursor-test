package com.radiogarden

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.radiogarden.ui.theme.RadioGardenTheme
import com.radiogarden.ui.screens.RadioGardenScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RadioGardenTheme {
                RadioGardenScreen()
            }
        }
    }
}
