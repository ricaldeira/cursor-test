package com.carmobileapp.car

import androidx.car.app.Session
import com.carmobileapp.car.session.CarSession
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarSessionFactory @Inject constructor() {
    fun create(): Session {
        return CarSession()
    }
}