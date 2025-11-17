package com.example.spacexlaunches.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexlaunches.data.LaunchRepository
import com.example.spacexlaunches.data.local.LaunchEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedLaunchesViewModel @Inject constructor(
    private val repository: LaunchRepository
) : ViewModel() {

    var launches by mutableStateOf<List<LaunchEntity>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        loadLaunches()
    }

    fun loadLaunches() {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null
                launches = repository.getSavedLaunches()
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }
}
