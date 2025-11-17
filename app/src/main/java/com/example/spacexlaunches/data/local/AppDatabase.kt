package com.example.spacexlaunches.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LaunchEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun launchDao(): LaunchDao
}
