package com.camgist.scribbledash.core.presentation

/**
 * Contains all the navigation routes used in the app
 */
sealed class Route(val route: String) {
    // Drawing feature routes
    object DrawingScreen : Route("drawing_screen")
    object GameScreen : Route("game_screen")
    object LobbyScreen : Route("lobby_screen")
    object HomeScreen : Route("home_screen")
    object DifficultyScreen : Route("difficulty_screen")
    
    // User routes
    object ProfileScreen : Route("profile_screen")
    object AuthScreen : Route("auth_screen")
    
    // Misc routes
    object SplashScreen : Route("splash_screen")
    object SettingsScreen : Route("settings_screen")
} 