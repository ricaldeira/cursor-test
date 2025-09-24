package com.carmobileapp.car.presentation

import androidx.car.app.Screen
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarHomeScreenFactory @Inject constructor() {
    fun create(): Screen {
        return CarHomeScreen()
    }
}