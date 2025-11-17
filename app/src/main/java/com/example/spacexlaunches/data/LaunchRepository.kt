package com.example.spacexlaunches.data

import com.example.spacexlaunches.data.local.LaunchDao
import com.example.spacexlaunches.data.local.LaunchEntity
import com.example.spacexlaunches.data.remote.dto.SpaceXApi
import javax.inject.Inject

class LaunchRepository @Inject constructor(
    private val api: SpaceXApi,
    private val dao: LaunchDao
) {

    // Загрузить запуски за год из API и сохранить в БД
    suspend fun fetchLaunchesForYear(year: Int) {
        val allLaunches = api.getAllLaunches()
        val filtered = allLaunches.filter { dto ->
            dto.date_utc?.take(4)?.toIntOrNull() == year
        }
        dao.clearAll()
        dao.insertLaunches(filtered.map { it.toEntity() })
    }

    // Получить сохранённые запуски из БД
    suspend fun getSavedLaunches(): List<LaunchEntity> =
        dao.getAllLaunches()
}
