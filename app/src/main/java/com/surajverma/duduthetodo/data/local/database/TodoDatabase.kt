package com.surajverma.duduthetodo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.surajverma.duduthetodo.data.local.dao.TodoDao
import com.surajverma.duduthetodo.data.local.entity.TodoEntity

@Database(
    entities = [TodoEntity::class],
    version = 1,
    exportSchema = false
)

abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {
        const val DATABASE_NAME = "todo_db"
    }
}