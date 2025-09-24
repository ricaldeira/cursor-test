package com.radiogarden.car.session

import androidx.car.app.Session
import androidx.car.app.Screen
import com.radiogarden.car.presentation.CarHomeScreen
import com.radiogarden.car.presentation.CarHomeScreenFactory

class CarSession : Session() {
    
    private val carHomeScreenFactory = CarHomeScreenFactory()
    
    override fun onCreateScreen(intent: android.content.Intent): Screen {
        return carHomeScreenFactory.create(carContext)
    }
}
