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
fun MapsScreen(navController: NavHostController) {
    val maps = listOf(
        GameMap(
            name = stringResource(R.string.map_ascent_name),
            description = stringResource(R.string.map_ascent_desc),
            imageResId = R.drawable.ascent
        ),
        GameMap(
            name = stringResource(R.string.map_bind_name),
            description = stringResource(R.string.map_bind_desc),
            imageResId = R.drawable.bind
        ),
        GameMap(
            name = stringResource(R.string.map_haven_name),
            description = stringResource(R.string.map_haven_desc),
            imageResId = R.drawable.haven
        ),
        GameMap(
            name = stringResource(R.string.map_split_name),
            description = stringResource(R.string.map_split_desc),
            imageResId = R.drawable.split
        ),
        GameMap(
            name = stringResource(R.string.map_fracture_name),
            description = stringResource(R.string.map_fracture_desc),
            imageResId = R.drawable.fracture
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = stringResource(R.string.maps_list), style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        maps.forEach { map ->
            Button(onClick = {
                navController.navigate("mapDetail/${map.name}")
            }) {
                Text(map.name)
            }
        }
    }
}

@Composable
fun MapDetailScreen(mapName: String, navController: NavHostController) {
    val maps = listOf(
        GameMap(stringResource(R.string.map_ascent_name), stringResource(R.string.map_ascent_desc), R.drawable.ascent),
        GameMap(stringResource(R.string.map_bind_name), stringResource(R.string.map_bind_desc), R.drawable.bind),
        GameMap(stringResource(R.string.map_haven_name), stringResource(R.string.map_haven_desc), R.drawable.haven),
        GameMap(stringResource(R.string.map_split_name), stringResource(R.string.map_split_desc), R.drawable.split),
        GameMap(stringResource(R.string.map_fracture_name), stringResource(R.string.map_fracture_desc), R.drawable.fracture)
    )

    val map = maps.find { it.name == mapName }

    map?.let {
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