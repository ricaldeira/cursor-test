package com.carmobileapp.mobile.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.carmobileapp.mobile.presentation.home.HomeScreen
import com.carmobileapp.mobile.presentation.navigation.NavigationScreen
import com.carmobileapp.mobile.presentation.profile.ProfileScreen
import com.carmobileapp.mobile.presentation.vehicles.VehiclesScreen

@Composable
fun MobileNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = MobileScreen.Home.route,
        modifier = modifier
    ) {
        composable(MobileScreen.Home.route) {
            HomeScreen(
                onNavigateToNavigation = {
                    navController.navigate(MobileScreen.Navigation.route)
                },
                onNavigateToVehicles = {
                    navController.navigate(MobileScreen.Vehicles.route)
                },
                onNavigateToProfile = {
                    navController.navigate(MobileScreen.Profile.route)
                }
            )
        }
        
        composable(MobileScreen.Navigation.route) {
            NavigationScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable(MobileScreen.Vehicles.route) {
            VehiclesScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable(MobileScreen.Profile.route) {
            ProfileScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}