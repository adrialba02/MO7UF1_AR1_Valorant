@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.companionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.companionapp.ui.theme.CompanionAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.google.firebase.database.FirebaseDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }
            CompanionAppTheme(darkTheme = isDarkTheme) {
                MainScreen(onToggleTheme = { isDarkTheme = !isDarkTheme })
            }
        }
    }
}

@Composable
fun SettingsScreen(onToggleTheme: () -> Unit, onLogout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.settings), style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onToggleTheme) {
            Text(stringResource(R.string.change_theme))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onLogout) {
            Text(stringResource(R.string.logout))
        }
    }
}


fun saveUserToFirebase(user: User) {
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.getReference("Users")

    usersRef.child(user.username).setValue(user)
        .addOnSuccessListener {
            println("Guardado correctamente.")
        }
        .addOnFailureListener { e ->
            println("Error al guardar el usuario")
        }
}


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    var isDarkTheme by remember { mutableStateOf(false) }
    CompanionAppTheme(darkTheme = isDarkTheme) {
        MainScreen(onToggleTheme = { isDarkTheme = !isDarkTheme })
    }
}
