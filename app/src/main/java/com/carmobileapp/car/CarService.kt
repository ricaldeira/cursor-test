package com.carmobileapp.car

import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarService : CarAppService() {
    
    @Inject
    lateinit var carSessionFactory: CarSessionFactory
    
    override fun createHostValidator(): HostValidator {
        return if (applicationInfo.flags and android.content.pm.ApplicationInfo.FLAG_DEBUGGABLE != 0) {
            HostValidator.ALLOW_ALL_HOSTS_VALIDATOR
        } else {
            HostValidator.createAllowlistValidator(
                "com.google.android.projection.gearhead"
            )
        }
    }
    
    override fun onCreateSession(): Session {
        return carSessionFactory.create()
    }
}