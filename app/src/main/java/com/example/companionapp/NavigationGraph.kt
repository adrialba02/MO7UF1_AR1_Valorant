package com.example.companionapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.companionapp.*

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onToggleTheme: () -> Unit,
    isLoggedIn: Boolean,
    user: User?,
    onLoginSuccess: (User) -> Unit,
    onLogout: () -> Unit
) {
    NavHost(navController, startDestination = "home", modifier = modifier) {
        composable("home") { HomeScreen(onLoginSuccess, isLoggedIn, user) }

        if (isLoggedIn) {
            composable("agents") { AgentsScreen(navController) }
            composable("agentDetail/{agentName}") { backStackEntry ->
                val agentName = backStackEntry.arguments?.getString("agentName") ?: ""
                AgentDetailScreen(agentName, navController)
            }
            composable("weapons") { WeaponsScreen(navController) }
            composable("weaponDetail/{weaponName}") { backStackEntry ->
                val weaponName = backStackEntry.arguments?.getString("weaponName") ?: ""
                WeaponDetailScreen(weaponName, navController)
            }
            composable("maps") { MapsScreen(navController) }
            composable("mapDetail/{mapName}") { backStackEntry ->
                val mapName = backStackEntry.arguments?.getString("mapName") ?: ""
                MapDetailScreen(mapName, navController)
            }
            composable("settings") { SettingsScreen(onToggleTheme, onLogout) }
        }
    }
}