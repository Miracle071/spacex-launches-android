package com.example.spacexlaunches.data

import com.example.spacexlaunches.data.local.LaunchEntity
import com.example.spacexlaunches.data.remote.dto.LaunchDto

fun LaunchDto.toEntity(): LaunchEntity =
    LaunchEntity(
        id = id,
        name = name ?: "Unknown",
        dateUtc = date_utc ?: "",
        success = success,
        details = details
    )
