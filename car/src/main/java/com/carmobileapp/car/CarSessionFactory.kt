package com.carmobileapp.car

import androidx.car.app.Session
import com.carmobileapp.car.session.CarSession

class CarSessionFactory {
    fun create(): Session {
        return CarSession()
    }
}
