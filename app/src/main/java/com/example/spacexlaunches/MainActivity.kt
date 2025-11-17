package com.example.spacexlaunches

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spacexlaunches.presentation.LaunchInputScreen
import com.example.spacexlaunches.presentation.LaunchInputViewModel
import com.example.spacexlaunches.presentation.SavedLaunchesScreen
import com.example.spacexlaunches.presentation.SavedLaunchesViewModel
import com.example.spacexlaunches.ui.theme.SpaceXLaunchesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceXLaunchesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SpaceXNavHost()
                }
            }
        }
    }
}

@Composable
fun SpaceXNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "input"
    ) {
        composable("input") {
            val vm: LaunchInputViewModel = hiltViewModel()
            LaunchInputScreen(
                viewModel = vm,
                onNavigateToList = { navController.navigate("list") }
            )
        }
        composable("list") {
            val vm: SavedLaunchesViewModel = hiltViewModel()
            SavedLaunchesScreen(
                viewModel = vm,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
