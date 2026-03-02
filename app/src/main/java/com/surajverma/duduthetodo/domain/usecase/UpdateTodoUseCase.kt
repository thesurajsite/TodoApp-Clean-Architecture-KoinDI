package com.surajverma.duduthetodo.domain.usecase

import com.surajverma.duduthetodo.domain.model.Todo
import com.surajverma.duduthetodo.domain.repository.TodoRepository

class UpdateTodoUseCase(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(todo: Todo) {
        if (todo.title.isBlank()) {
            throw IllegalArgumentException("Plzz Enter the Title")
        }
        repository.updateTodo(todo)
    }
}