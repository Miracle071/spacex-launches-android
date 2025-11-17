package com.example.spacexlaunches.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedLaunchesScreen(
    viewModel: SavedLaunchesViewModel,
    onBack: () -> Unit
) {
    val launches = viewModel.launches
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Saved SpaceX launches") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                error != null -> {
                    Text(
                        text = error,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.error
                    )
                }
                launches.isEmpty() -> {
                    Text(
                        text = "No saved launches. Go back and load some.",
                        modifier = Modifier.padding(16.dp)
                    )
                }
                else -> {
                    LazyColumn {
                        items(launches) { launch ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                            ) {
                                Text(
                                    text = launch.name,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = "Date UTC: ${launch.dateUtc}")
                                Text(
                                    text = "Success: ${launch.success ?: false}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                                launch.details?.let {
                                    Text(
                                        text = it,
                                        maxLines = 3,
                                        overflow = TextOverflow.Ellipsis,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                            Divider()
                        }
                    }
                }
            }
        }
    }
}
