package com.carmobileapp.core.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val profileImageUrl: String? = null,
    val preferences: UserPreferences = UserPreferences()
)

data class UserPreferences(
    val theme: Theme = Theme.SYSTEM,
    val language: String = "en",
    val notificationsEnabled: Boolean = true,
    val carModeEnabled: Boolean = true
)

enum class Theme {
    LIGHT, DARK, SYSTEM
}