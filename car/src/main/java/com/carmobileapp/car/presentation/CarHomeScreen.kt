package com.carmobileapp.car.presentation

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.ActionStrip
import androidx.car.app.model.CarIcon
import androidx.car.app.model.GridTemplate
import androidx.car.app.model.GridItem
import androidx.car.app.model.ItemList
import androidx.car.app.model.Template
import androidx.car.app.navigation.model.NavigationTemplate
import androidx.core.graphics.drawable.IconCompat
import com.carmobileapp.car.presentation.navigation.CarNavigationScreen

class CarHomeScreen : Screen(carContext) {
    
    override fun onGetTemplate(): Template {
        val gridItems = ItemList.Builder()
            .addItem(
                GridItem.Builder()
                    .setTitle("Navigation")
                    .setText("Start navigation")
                    .setImage(
                        CarIcon.Builder(
                            IconCompat.createWithResource(
                                carContext,
                                android.R.drawable.ic_menu_directions
                            )
                        ).build()
                    )
                    .setOnClickListener {
                        screenManager.push(CarNavigationScreen(carContext))
                    }
                    .build()
            )
            .addItem(
                GridItem.Builder()
                    .setTitle("Music")
                    .setText("Play music")
                    .setImage(
                        CarIcon.Builder(
                            IconCompat.createWithResource(
                                carContext,
                                android.R.drawable.ic_media_play
                            )
                        ).build()
                    )
                    .setOnClickListener {
                        // TODO: Implement music screen
                    }
                    .build()
            )
            .addItem(
                GridItem.Builder()
                    .setTitle("Phone")
                    .setText("Make a call")
                    .setImage(
                        CarIcon.Builder(
                            IconCompat.createWithResource(
                                carContext,
                                android.R.drawable.ic_menu_call
                            )
                        ).build()
                    )
                    .setOnClickListener {
                        // TODO: Implement phone screen
                    }
                    .build()
            )
            .addItem(
                GridItem.Builder()
                    .setTitle("Settings")
                    .setText("App settings")
                    .setImage(
                        CarIcon.Builder(
                            IconCompat.createWithResource(
                                carContext,
                                android.R.drawable.ic_menu_preferences
                            )
                        ).build()
                    )
                    .setOnClickListener {
                        // TODO: Implement settings screen
                    }
                    .build()
            )
            .build()
        
        return GridTemplate.Builder()
            .setHeaderAction(Action.APP_ICON)
            .setSingleList(gridItems)
            .setActionStrip(
                ActionStrip.Builder()
                    .addAction(
                        Action.Builder()
                            .setTitle("Voice")
                            .setOnClickListener {
                                // TODO: Implement voice commands
                            }
                            .build()
                    )
                    .build()
            )
            .setTitle("Car Mobile App")
            .build()
    }
}