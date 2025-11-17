package com.example.spacexlaunches.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexlaunches.data.LaunchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchInputViewModel @Inject constructor(
    private val repository: LaunchRepository
) : ViewModel() {

    var yearInput by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun onYearChange(newYear: String) {
        yearInput = newYear
    }

    fun loadLaunches(onSuccess: () -> Unit) {
        val year = yearInput.toIntOrNull()
        if (year == null) {
            errorMessage = "Year must be a number"
            return
        }

        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null
                repository.fetchLaunchesForYear(year)
                onSuccess()
            } catch (e: Exception) {
                errorMessage = e.message ?: "Unknown error"
            } finally {
                isLoading = false
            }
        }
    }
}
