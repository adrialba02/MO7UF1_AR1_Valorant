package com.example.companionapp

import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.companionapp.ui.theme.CompanionAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onLoginSuccess: (User) -> Unit,
    isLoggedIn: Boolean,
    user: User?,
    navController: NavController,
    drawerState: DrawerState, // Añadido para controlar el estado del drawer
    scope: CoroutineScope
) {
    var enteredUsername by rememberSaveable { mutableStateOf("") }
    var enteredPassword by rememberSaveable { mutableStateOf("") }
    var enteredLevel by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    if (isLoggedIn && user != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¡${stringResource(R.string.welcome)}, ${user.username}! Nivel: ${user.level}",
                style = MaterialTheme.typography.headlineLarge
            )
            Button(
                onClick = {
                    scope.launch { drawerState.open() } // Abre el drawer
                },
                shape = RoundedCornerShape(50)
            ) {
                Text("Abrir Panel de Navegación")
            }
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.welcome), style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = enteredUsername,
            onValueChange = { enteredUsername = it },
            label = { Text(stringResource(R.string.username)) },
            shape = RoundedCornerShape(50),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = enteredPassword,
            onValueChange = { enteredPassword = it },
            label = { Text(stringResource(R.string.password)) },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Image(
                        painter = painterResource(id = if (passwordVisible) R.drawable.eye_open else R.drawable.eye_closed),
                        contentDescription = stringResource(if (passwordVisible) R.string.hide_password else R.string.show_password),
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            shape = RoundedCornerShape(50),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = enteredLevel,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) enteredLevel = newValue
            },
            label = { Text(stringResource(R.string.level)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(50),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (enteredUsername.isNotBlank() && enteredPassword.isNotBlank() && enteredLevel.isNotBlank()) {
                    val newUser = User(enteredUsername, enteredPassword, enteredLevel)
                    saveUserToFirebase(newUser)
                    onLoginSuccess(newUser)
                }
            },
            enabled = enteredUsername.isNotBlank() && enteredPassword.isNotBlank() && enteredLevel.isNotBlank(),
            shape = RoundedCornerShape(50)
        ) {
            Text(stringResource(R.string.login))
        }
    }
}

