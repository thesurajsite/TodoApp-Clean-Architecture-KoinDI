package com.surajverma.duduthetodo.domain.usecase

import com.surajverma.duduthetodo.domain.model.Todo
import com.surajverma.duduthetodo.domain.repository.TodoRepository

class DeleteTodoUseCase(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(todo: Todo) {
        repository.deleteTodo(todo)
    }
}