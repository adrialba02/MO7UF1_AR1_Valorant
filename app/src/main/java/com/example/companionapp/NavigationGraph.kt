package com.example.companionapp

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.companionapp.*
import kotlinx.coroutines.CoroutineScope

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onToggleTheme: () -> Unit,
    isLoggedIn: Boolean,
    user: User?,
    onLoginSuccess: (User) -> Unit,
    onLogout: () -> Unit,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    NavHost(navController, startDestination = "home", modifier = modifier) {
        composable("home") { HomeScreen(onLoginSuccess, isLoggedIn, user, navController, drawerState, scope) }

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