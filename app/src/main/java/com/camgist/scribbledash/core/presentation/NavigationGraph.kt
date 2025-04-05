package com.camgist.scribbledash.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.camgist.scribbledash.feature_drawing.presentation.screens.DifficultyScreen
import com.camgist.scribbledash.feature_drawing.presentation.screens.DrawingScreen
import com.camgist.scribbledash.feature_drawing.presentation.screens.HomeScreen
import com.camgist.scribbledash.feature_drawing.presentation.screens.SplashScreen

/**
 * Main navigation graph for the app
 */
@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String = Route.SplashScreen.route,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // Drawing feature routes
        composable(Route.DrawingScreen.route) {
            DrawingScreen(navController = navController)
        }
        
        composable(Route.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        
        composable(Route.DifficultyScreen.route) {
            DifficultyScreen(navController = navController)
        }
        
        composable(Route.GameScreen.route) {
            // GameScreen will be implemented later
        }
        
        composable(Route.LobbyScreen.route) {
            // LobbyScreen will be implemented later
        }
        
        // User routes
        composable(Route.ProfileScreen.route) {
            // ProfileScreen will be implemented later
        }
        
        composable(Route.AuthScreen.route) {
            // AuthScreen will be implemented later
        }
        
        // Misc routes
        composable(Route.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        
        composable(Route.SettingsScreen.route) {
            // SettingsScreen will be implemented later
        }
    }
} 