package com.example.spacexlaunches.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "launches")
data class LaunchEntity(
    @PrimaryKey val id: String,
    val name: String,
    val dateUtc: String,
    val success: Boolean?,
    val details: String?
)
