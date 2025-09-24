package com.radiogarden.car.presentation

import androidx.car.app.CarContext
import androidx.car.app.Screen

class CarHomeScreenFactory {
    fun create(carContext: CarContext): Screen {
        return CarHomeScreen(carContext)
    }
}
