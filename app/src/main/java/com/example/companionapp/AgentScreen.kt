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
fun AgentsScreen(navController: NavHostController) {
    val agents = listOf(
        Agente(
            name = stringResource(R.string.agent_jett_name),
            description = stringResource(R.string.agent_jett_desc),
            imageResId = R.drawable.jett
        ),
        Agente(
            name = stringResource(R.string.agent_phoenix_name),
            description = stringResource(R.string.agent_phoenix_desc),
            imageResId = R.drawable.fade
        ),
        Agente(
            name = stringResource(R.string.agent_sova_name),
            description = stringResource(R.string.agent_sova_desc),
            imageResId = R.drawable.sova
        ),
        Agente(
            name = stringResource(R.string.agent_viper_name),
            description = stringResource(R.string.agent_viper_desc),
            imageResId = R.drawable.chamber
        ),
        Agente(
            name = stringResource(R.string.agent_cypher_name),
            description = stringResource(R.string.agent_cypher_desc),
            imageResId = R.drawable.cypher
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = stringResource(R.string.agent_list), style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        agents.forEach { agent ->
            Button(onClick = {
                navController.navigate("agentDetail/${agent.name}")
            }) {
                Text(agent.name)
            }
        }
    }
}

@Composable
fun AgentDetailScreen(agentName: String, navController: NavHostController) {
    val agents = listOf(
        Agente(stringResource(R.string.agent_jett_name), stringResource(R.string.agent_jett_desc), R.drawable.jett),
        Agente(stringResource(R.string.agent_phoenix_name), stringResource(R.string.agent_phoenix_desc), R.drawable.fade),
        Agente(stringResource(R.string.agent_sova_name), stringResource(R.string.agent_sova_desc), R.drawable.sova),
        Agente(stringResource(R.string.agent_viper_name), stringResource(R.string.agent_viper_desc), R.drawable.chamber),
        Agente(stringResource(R.string.agent_cypher_name), stringResource(R.string.agent_cypher_desc), R.drawable.cypher)
    )

    val agent = agents.find { it.name == agentName }

    agent?.let {
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
    } ?: run {
        Text(stringResource(R.string.agent_not_found))
    }
}