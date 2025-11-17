package com.example.spacexlaunches.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LaunchDao {

    @Query("SELECT * FROM launches ORDER BY dateUtc DESC")
    suspend fun getAllLaunches(): List<LaunchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLaunches(launches: List<LaunchEntity>)

    @Query("DELETE FROM launches")
    suspend fun clearAll()
}
