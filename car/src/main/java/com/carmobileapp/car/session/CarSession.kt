package com.carmobileapp.car.session

import androidx.car.app.Session
import androidx.car.app.Screen
import com.carmobileapp.car.presentation.CarHomeScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarSession : Session() {
    
    @Inject
    lateinit var carHomeScreenFactory: CarHomeScreenFactory
    
    override fun onCreateScreen(intent: android.content.Intent): Screen {
        return carHomeScreenFactory.create()
    }
}