package com.nuagaon.telisahi.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Families : Screen("families")
    object Polisava : Screen("polisava")
    object Updates : Screen("updates")
    object Settings : Screen("settings")
}
