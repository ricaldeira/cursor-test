package com.radiogarden.car

import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator

class CarService : CarAppService() {
    
    private val carSessionFactory = CarSessionFactory()
    
    override fun createHostValidator(): HostValidator {
        return HostValidator.ALLOW_ALL_HOSTS_VALIDATOR
    }
    
    override fun onCreateSession(): Session {
        return carSessionFactory.create()
    }
}
