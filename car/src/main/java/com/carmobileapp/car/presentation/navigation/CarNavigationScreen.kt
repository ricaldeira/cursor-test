package com.radiogarden.car.presentation.navigation

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.ActionStrip
import androidx.car.app.model.CarIcon
import androidx.car.app.model.MessageTemplate
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.core.graphics.drawable.IconCompat

class CarNavigationScreen(carContext: CarContext) : Screen(carContext) {
    
    override fun onGetTemplate(): Template {
        val rows = listOf(
            Row.Builder()
                .setTitle("Home")
                .addText("123 Main St, City")
                .setOnClickListener {
                    // TODO: Navigate to home
                }
                .build(),
            Row.Builder()
                .setTitle("Work")
                .addText("456 Business Ave, City")
                .setOnClickListener {
                    // TODO: Navigate to work
                }
                .build(),
            Row.Builder()
                .setTitle("Recent Destination")
                .addText("789 Recent St, City")
                .setOnClickListener {
                    // TODO: Navigate to recent destination
                }
                .build()
        )
        
        val paneBuilder = androidx.car.app.model.Pane.Builder()
            .addAction(
                Action.Builder()
                    .setTitle("Search")
                    .setOnClickListener {
                        // TODO: Open search
                    }
                    .build()
            )
            .addAction(
                Action.Builder()
                    .setTitle("Favorites")
                    .setOnClickListener {
                        // TODO: Open favorites
                    }
                    .build()
            )
        
        // Add rows individually
        rows.forEach { row ->
            paneBuilder.addRow(row)
        }
        
        return PaneTemplate.Builder(paneBuilder.build())
            .setHeaderAction(Action.BACK)
            .setActionStrip(
                ActionStrip.Builder()
                    .addAction(
                        Action.Builder()
                            .setTitle("Voice Search")
                            .setOnClickListener {
                                // TODO: Implement voice search
                            }
                            .build()
                    )
                    .build()
            )
            .setTitle("Navigation")
            .build()
    }
}
