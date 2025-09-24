package com.carmobileapp.mobile.navigation

sealed class MobileScreen(val route: String) {
    object Home : MobileScreen("home")
    object Navigation : MobileScreen("navigation")
    object Vehicles : MobileScreen("vehicles")
    object Profile : MobileScreen("profile")
}