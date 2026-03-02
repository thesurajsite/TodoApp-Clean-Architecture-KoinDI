package com.surajverma.duduthetodo.data.repository

import com.surajverma.duduthetodo.data.local.dao.TodoDao
import com.surajverma.duduthetodo.data.local.entity.toDomainModel
import com.surajverma.duduthetodo.data.local.entity.toEntity
import com.surajverma.duduthetodo.domain.model.Todo
import com.surajverma.duduthetodo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(private val dao: TodoDao) : TodoRepository {

    override fun getAllTodos(): Flow<List<Todo>> {
        return dao.getAllTodos().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)?.toDomainModel()
    }

    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo.toEntity())
    }

    override suspend fun updateTodo(todo: Todo) {
        dao.updateTodo(todo.toEntity())
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo.toEntity())
    }

    override suspend fun toggleTodoCompletion(id: Int) {
        dao.toggleTodoCompletion(id)
    }
}