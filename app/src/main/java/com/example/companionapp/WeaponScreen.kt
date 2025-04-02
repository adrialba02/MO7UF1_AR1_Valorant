package com.example.companionapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun WeaponsScreen(navController: NavHostController) {
    val weapons = listOf(
        Weapon(
            name = stringResource(R.string.weapon_vandal_name),
            description = stringResource(R.string.weapon_vandal_desc),
            imageResId = R.drawable.vandal
        ),
        Weapon(
            name = stringResource(R.string.weapon_phantom_name),
            description = stringResource(R.string.weapon_phantom_desc),
            imageResId = R.drawable.phantom
        ),
        Weapon(
            name = stringResource(R.string.weapon_operator_name),
            description = stringResource(R.string.weapon_operator_desc),
            imageResId = R.drawable.operator
        ),
        Weapon(
            name = stringResource(R.string.weapon_sheriff_name),
            description = stringResource(R.string.weapon_sheriff_desc),
            imageResId = R.drawable.sheriff
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = stringResource(R.string.weapons_list), style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        weapons.forEach { weapon ->
            Button(onClick = {
                navController.navigate("weaponDetail/${weapon.name}")
            }) {
                Text(weapon.name)
            }
        }
    }
}

@Composable
fun WeaponDetailScreen(weaponName: String, navController: NavHostController) {
    val weapons = listOf(
        Weapon(stringResource(R.string.weapon_vandal_name), stringResource(R.string.weapon_vandal_desc), R.drawable.vandal),
        Weapon(stringResource(R.string.weapon_phantom_name), stringResource(R.string.weapon_phantom_desc), R.drawable.phantom),
        Weapon(stringResource(R.string.weapon_operator_name), stringResource(R.string.weapon_operator_desc), R.drawable.operator),
        Weapon(stringResource(R.string.weapon_sheriff_name), stringResource(R.string.weapon_sheriff_desc), R.drawable.sheriff)
    )

    val weapon = weapons.find { it.name == weaponName }

    weapon?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = it.name, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = it.imageResId),
                contentDescription = it.name,
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = it.description, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text(stringResource(R.string.go_back))
            }
        }
    }
}
