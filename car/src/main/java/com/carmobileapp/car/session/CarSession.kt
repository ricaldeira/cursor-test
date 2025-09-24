package com.carmobileapp.car.session

import androidx.car.app.Session
import androidx.car.app.Screen
import com.carmobileapp.car.presentation.CarHomeScreen
import com.carmobileapp.car.presentation.CarHomeScreenFactory

class CarSession : Session() {
    
    private val carHomeScreenFactory = CarHomeScreenFactory()
    
    override fun onCreateScreen(intent: android.content.Intent): Screen {
        return carHomeScreenFactory.create(carContext)
    }
}
