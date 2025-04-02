package com.example.companionapp

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerContent(navController: NavHostController, drawerState: DrawerState, scope: CoroutineScope) {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .width(220.dp)
            .background(MaterialTheme.colorScheme.surface),
        shadowElevation = 8.dp
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(30.dp)) {
            Text(stringResource(R.string.menu), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)

            val menuItems = listOf(
                stringResource(R.string.home) to "home",
                stringResource(R.string.agents) to "agents",
                stringResource(R.string.weapons) to "weapons",
                stringResource(R.string.maps) to "maps",
                stringResource(R.string.settings) to "settings"
            )

            menuItems.forEach { (title, route) ->
                TextButton(
                    onClick = {
                        navController.navigate(route) {
                            popUpTo("home") { inclusive = false }
                            launchSingleTop = true
                        }
                        scope.launch { drawerState.close() }
                    }
                ) {
                    Text(title)
                }
            }
        }
    }
}