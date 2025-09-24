package com.carmobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.carmobileapp.mobile.navigation.MobileNavigation
import com.carmobileapp.ui.theme.CarMobileAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarMobileAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MobileNavigation(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}