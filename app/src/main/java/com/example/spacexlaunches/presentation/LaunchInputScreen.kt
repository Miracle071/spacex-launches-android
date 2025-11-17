package com.example.spacexlaunches.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LaunchInputScreen(
    viewModel: LaunchInputViewModel,
    onNavigateToList: () -> Unit
) {
    val year = viewModel.yearInput
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "SpaceX launches by year",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = year,
            onValueChange = viewModel::onYearChange,
            label = { Text("Year (e.g. 2020)") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { viewModel.loadLaunches(onNavigateToList) },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(18.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text("Load and save launches")
            }
        }

        error?.let {
            Spacer(Modifier.height(8.dp))
            Text(text = it, color = Color.Red)
        }
    }
}
