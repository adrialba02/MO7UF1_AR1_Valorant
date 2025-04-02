
package com.example.companionapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onToggleTheme: () -> Unit) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var isLoggedIn by rememberSaveable { mutableStateOf(false) }
    var user by rememberSaveable { mutableStateOf<User?>(null) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = isLoggedIn,
        drawerContent = {
            if (isLoggedIn) {
                DrawerContent(navController, drawerState, scope)
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name), color = MaterialTheme.colorScheme.primary) },
                    navigationIcon = {
                        if (isLoggedIn) {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Filled.Menu, contentDescription = stringResource(R.string.menu), tint = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                )
            }
        ) { innerPadding ->
            NavigationGraph(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                onToggleTheme = onToggleTheme,
                isLoggedIn = isLoggedIn,
                user = user,
                onLoginSuccess = { newUser ->
                    user = newUser
                    isLoggedIn = true
                },
                onLogout = {
                    isLoggedIn = false
                    user = null
                }
            )
        }
    }
}