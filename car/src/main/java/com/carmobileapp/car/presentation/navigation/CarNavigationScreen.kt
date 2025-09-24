package com.carmobileapp.car.presentation.navigation

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
                .setText("123 Main St, City")
                .setOnClickListener {
                    // TODO: Navigate to home
                }
                .build(),
            Row.Builder()
                .setTitle("Work")
                .setText("456 Business Ave, City")
                .setOnClickListener {
                    // TODO: Navigate to work
                }
                .build(),
            Row.Builder()
                .setTitle("Recent Destination")
                .setText("789 Recent St, City")
                .setOnClickListener {
                    // TODO: Navigate to recent destination
                }
                .build()
        )
        
        return PaneTemplate.Builder(
            androidx.car.app.model.Pane.Builder()
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
                .setRows(rows)
                .build()
        )
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