package com.example.spacexlaunches.data.remote.dto

data class LaunchDto(
    val id: String,
    val name: String?,
    val date_utc: String?,
    val success: Boolean?,
    val details: String?
)
