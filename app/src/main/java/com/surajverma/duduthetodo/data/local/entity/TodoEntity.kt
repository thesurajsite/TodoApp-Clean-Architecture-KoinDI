package com.surajverma.duduthetodo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.surajverma.duduthetodo.domain.model.Todo


@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val createdAt: Long
)

fun TodoEntity.toDomainModel(): Todo {
    return Todo(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
        createdAt = createdAt
    )
}

fun Todo.toEntity(): TodoEntity {
    return TodoEntity(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
        createdAt = createdAt
    )
}