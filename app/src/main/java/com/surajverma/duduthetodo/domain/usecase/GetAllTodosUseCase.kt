package com.surajverma.duduthetodo.domain.usecase

import com.surajverma.duduthetodo.domain.model.Todo
import com.surajverma.duduthetodo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetAllTodosUseCase(
    private val repository: TodoRepository
) {
    operator fun invoke(): Flow<List<Todo>> {
        return repository.getAllTodos()
    }
}