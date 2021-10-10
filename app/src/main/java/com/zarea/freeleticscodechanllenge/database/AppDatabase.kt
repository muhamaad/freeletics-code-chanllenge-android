package com.zarea.freeleticscodechanllenge.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zarea.freeleticscodechanllenge.model.RepoResponse

/**
 * Created by zarea at 2021
 */

@Database(entities = [RepoResponse::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun RepoDao(): RepoDao
}
