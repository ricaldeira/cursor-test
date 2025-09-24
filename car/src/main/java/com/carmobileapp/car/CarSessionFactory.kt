package com.radiogarden.car

import androidx.car.app.Session
import com.radiogarden.car.session.CarSession

class CarSessionFactory {
    fun create(): Session {
        return CarSession()
    }
}
