package com.surajverma.duduthetodo.domain.usecase

import com.surajverma.duduthetodo.domain.repository.TodoRepository

class ToggleTodoCompletionUseCase(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(todoId: Int) {
        repository.toggleTodoCompletion(todoId)
    }
}