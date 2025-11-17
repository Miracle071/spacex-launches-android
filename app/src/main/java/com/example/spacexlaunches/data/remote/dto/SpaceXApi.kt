package com.example.spacexlaunches.data.remote.dto

import retrofit2.http.GET

interface SpaceXApi {

    // Получить все запуски
    @GET("v5/launches")
    suspend fun getAllLaunches(): List<LaunchDto>

    // Последний запуск (на будущее, для проверки)
    @GET("v5/launches/latest")
    suspend fun getLatestLaunch(): LaunchDto
}
